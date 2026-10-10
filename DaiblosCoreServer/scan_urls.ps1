# Scan global-metadata.dat string literal table, list all http/https strings
$md = "d:\Code\github\DataTable\DaiblosCoreServer\apktool_out\assets\bin\Data\Managed\Metadata\global-metadata.dat"
$bytes = [System.IO.File]::ReadAllBytes($md)

function U32($off) { [BitConverter]::ToUInt32($bytes, $off) }

$sanity = U32 0; $ver = U32 4
# header: 0x00 sanity, 0x04 ver, 0x08 literalTableOff, 0x0C literalTableSize,
#         0x10 literalDataOff, 0x14 literalDataSize
$slOff = U32 0x08; $slSize = U32 0x0C; $sldOff = U32 0x10
Write-Host "sanity=0x$($sanity.ToString('X')) ver=$ver slOff=0x$($slOff.ToString('X')) slSize=$slSize sldOff=0x$($sldOff.ToString('X'))"

$count = $slSize / 8
$hits = New-Object System.Collections.Generic.List[string]
for ($i = 0; $i -lt $count; $i++) {
    $len = [BitConverter]::ToInt32($bytes, $slOff + $i * 8)
    $di  = [BitConverter]::ToInt32($bytes, $slOff + $i * 8 + 4)
    if ($len -le 0 -or $len -gt 2048) { continue }
    if ($sldOff + $di + $len -gt $bytes.Length) { continue }
    $s = [System.Text.Encoding]::UTF8.GetString($bytes, $sldOff + $di, $len)
    if ($s -match 'https?://') {
        $hits.Add("ENTRY#$i len=$len :: $s")
    }
}
Write-Host "---- total URL literals: $($hits.Count) ----"
$hits | ForEach-Object { Write-Host $_ }
