# Scan literal table for MJSDK-related keywords
$md = "d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\assets\bin\Data\Managed\Metadata\global-metadata.dat"
$bytes = [System.IO.File]::ReadAllBytes($md)

function U32($off) { [BitConverter]::ToUInt32($bytes, $off) }
$slOff = U32 0x08; $slSize = U32 0x0C; $sldOff = U32 0x10
$count = $slSize / 8

$kw = 'zlongame|megagame|mjsdk|mjapi|gamebeans|sdk.*url|url.*sdk|\.tw|init.*url|\.php'
$rx = [regex]$kw
$out = New-Object System.Collections.Generic.List[string]
for ($i = 0; $i -lt $count; $i++) {
    $len = [BitConverter]::ToInt32($bytes, $slOff + $i * 8)
    $di  = [BitConverter]::ToInt32($bytes, $slOff + $i * 8 + 4)
    if ($len -le 0 -or $len -gt 2048) { continue }
    if ($sldOff + $di + $len -gt $bytes.Length) { continue }
    $s = [System.Text.Encoding]::UTF8.GetString($bytes, $sldOff + $di, $len)
    if ($rx.IsMatch($s.ToLower())) {
        $out.Add("ENTRY#$i len=$len :: $s")
    }
}
Write-Host "---- keyword hits: $($out.Count) ----"
$out | ForEach-Object { Write-Host $_ }
