# Decrypt MJInfo_Android.bin (AES-128-CBC key=iv=KGJDkn689Fu3JAnd), patch URLs, re-encrypt
$ErrorActionPreference = 'Stop'
$bin = "d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\assets\MJInfo_Android.bin"
$jsonOut = "d:\Code\github\DataTable\DaiblosCoreServer\MJInfo_Android.decrypted.json"
$keyAscii = 'KGJDkn689Fu3JAnd'
$key = [System.Text.Encoding]::ASCII.GetBytes($keyAscii)

function AesDec([byte[]]$enc, [byte[]]$k) {
    $aes = [System.Security.Cryptography.Aes]::Create()
    $aes.KeySize = 128; $aes.Key = $k; $aes.IV = $k
    $aes.Mode = 'CBC'; $aes.Padding = 'None'
    $dec = $aes.CreateDecryptor().TransformFinalBlock($enc, 0, $enc.Length)
    $aes.Dispose(); return $dec
}
function AesEnc([byte[]]$plain, [byte[]]$k) {
    $aes = [System.Security.Cryptography.Aes]::Create()
    $aes.KeySize = 128; $aes.Key = $k; $aes.IV = $k
    $aes.Mode = 'CBC'; $aes.Padding = 'None'
    $enc = $aes.CreateEncryptor().TransformFinalBlock($plain, 0, $plain.Length)
    $aes.Dispose(); return $enc
}

$data = [System.IO.File]::ReadAllBytes($bin)
if ($data.Length % 16 -ne 0) { throw "not multiple of 16" }
$plain = AesDec $data $key
$json = [System.Text.Encoding]::UTF8.GetString($plain)
[System.IO.File]::WriteAllText($jsonOut, $json, (New-Object System.Text.UTF8Encoding($false)))
Write-Host "decrypted -> $jsonOut ($($json.Length) chars)"
Write-Host "---- original JSON ----"
Write-Host $json

# patch: replace every https?://... value with local mock, keep JSON structure
$patched = [regex]::Replace($json, '"(url_[a-z_]+)"\s*:\s*"[^"]*"', { param($m) '"' + $m.Groups[1].Value + '": "http://127.0.0.1:8000/"' })
Write-Host "---- patched JSON ----"
Write-Host $patched

# pad with newlines back to original length (multiple of 16)
$pb = [System.Text.Encoding]::UTF8.GetBytes($patched)
if ($pb.Length -gt $data.Length) { throw ("patched longer: " + $pb.Length) }
$padded = New-Object byte[] $data.Length
[Array]::Copy($pb, $padded, $pb.Length)
for ($i = $pb.Length; $i -lt $padded.Length; $i++) { $padded[$i] = 0x0A }

$enc2 = AesEnc $padded $key
[System.IO.File]::WriteAllBytes($bin, $enc2)
Write-Host "re-encrypted -> $bin ($($enc2.Length) bytes)"

# verify roundtrip
$verify = [System.Text.Encoding]::UTF8.GetString((AesDec (AesEnc $padded $key) $key))
if ($verify -eq $patched) { Write-Host "roundtrip OK" } else { Write-Host "roundtrip MISMATCH" }
