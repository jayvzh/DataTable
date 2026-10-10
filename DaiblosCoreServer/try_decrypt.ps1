# Try to decrypt MJInfo_Android.bin: AES-128 with known keys, various modes, then XOR fallback
$f = "d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\assets\MJInfo_Android.bin"
$data = [System.IO.File]::ReadAllBytes($f)
Write-Host "size=$($data.Length) mod16=$($data.Length % 16)"

function Score([byte[]]$b) {
    $printable = 0
    $n = [Math]::Min(256, $b.Length)
    for ($i = 0; $i -lt $n; $i++) { if (($b[$i] -ge 32 -and $b[$i] -le 126) -or $b[$i] -eq 9 -or $b[$i] -eq 10 -or $b[$i] -eq 13) { $printable++ } }
    return $printable / $n
}
function TryAes([byte[]]$enc, [byte[]]$key, [string]$mode, [byte[]]$iv) {
    try {
        $aes = [System.Security.Cryptography.Aes]::Create()
        $aes.KeySize = 128; $aes.Key = $key
        if ($mode -eq 'ECB') { $aes.Mode = 'ECB' } else { $aes.Mode = 'CBC'; $aes.IV = $iv }
        $aes.Padding = 'None'
        $dec = $aes.CreateDecryptor().TransformFinalBlock($enc, 0, $enc.Length)
        $aes.Dispose()
        return $dec
    } catch { return $null }
}

$keys = @('KLJDkn789lu1J9A1', 'KGJDkn689Fu3JAnd')
$keyBytes = @{}
foreach ($k in $keys) { $keyBytes[$k] = [System.Text.Encoding]::ASCII.GetBytes($k) }

$blocks = New-Object System.Collections.Generic.List[object]
foreach ($k in $keys) {
    $kb = $keyBytes[$k]
    # whole file as ciphertext
    $blocks.Add(@{ name = "$k/full-ECB"; enc = $data; kb = $kb })
    $blocks.Add(@{ name = "$k/full-CBC-iv0"; enc = $data; kb = $kb })
    $blocks.Add(@{ name = "$k/full-CBC-ivKey"; enc = $data; kb = $kb })
    # skip first 16 bytes as IV
    $rest = $data[16..($data.Length-1)]
    $blocks.Add(@{ name = "$k/body-CBC-ivHead"; enc = $rest; kb = $kb })
}
foreach ($blk in $blocks) {
    $kb = $blk.kb; $enc = $blk.enc; $name = $blk.name
    $combos = @()
    if ($name -like '*ECB*') { $combos += ,@('ECB', $null) }
    if ($name -like '*iv0*') { $combos += ,@('CBC', (New-Object byte[] 16)) }
    if ($name -like '*ivKey*') { $combos += ,@('CBC', $kb) }
    if ($name -like '*ivHead*') { $combos += ,@('CBC', $data[0..15]) }
    foreach ($c in $combos) {
        $dec = TryAes $enc $kb $c[0] $c[1]
        if ($dec) {
            $s = Score $dec
            if ($s -gt 0.85) {
                Write-Host ("HIT {0} {1} score={2:P1}" -f $name, $c[0], $s)
                Write-Host ([System.Text.Encoding]::UTF8.GetString($dec).Substring(0, [Math]::Min(500, $dec.Length)))
            } else {
                Write-Host ("miss {0} {1} score={2:P1}" -f $name, $c[0], $s)
            }
        }
    }
}

# XOR fallback: repeating key over whole file
foreach ($k in $keys) {
    $kb = [System.Text.Encoding]::ASCII.GetBytes($k)
    $out = New-Object byte[] $data.Length
    for ($i = 0; $i -lt $data.Length; $i++) { $out[$i] = $data[$i] -bxor $kb[$i % $kb.Length] }
    $s = Score $out
    Write-Host ("xor {0} score={1:P1}" -f $k, $s)
    if ($s -gt 0.85) { Write-Host ([System.Text.Encoding]::UTF8.GetString($out).Substring(0, [Math]::Min(500, $out.Length))) }
}
