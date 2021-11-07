$npm = npm | Select-String -Pattern "is not recognized as the name of a cmdlet"
Write-Output $npm
if ($npm)
{
    Write-Output "Cannot run integration tests without npm"
    exit 1
}

$newman = newman --silent | Select-String -Pattern "is not recognized as the name of a cmdlet"
if ( $newman)
{
    Write-Output "Cannot run integration tests without newman installed\n Run npm install -g newman to install"
    exit 1
}

$mypath = $MyInvocation.MyCommand.Path | Split-Path -Parent

$collection = Get-ChildItem "$mypath" -Filter "*environment.json"
Get-ChildItem "$mypath" -Filter "*collection.json" |
ForEach-Object {
    Write-Output $_.FullName
    newman run $_.FullName --bail -e $collection.FullName
}