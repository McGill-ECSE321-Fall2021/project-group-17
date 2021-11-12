
function Clear_DB {
    Set-Variable -Name "server" -Value "postgres://lwhzzfglqzmohj:caf222c6ec61a394ac34989679eb98c5d432a664e68c5cf3c319199318f74954@ec2-44-198-154-255.compute-1.amazonaws.com:5432"
    Invoke-DBOQuery –Type PostgreSQL –Server $server  –Database ddsk1hvat3st1k –Query 'SELECT * from customer'
}


$npm = npm | Select-String -Pattern "is not recognized as the name of a cmdlet"
Write-Output $npm
if ($npm) {
    Write-Output "Cannot run integration tests without npm"
    exit 1
}

$newman = newman --silent | Select-String -Pattern "is not recognized as the name of a cmdlet"
if ( $newman) {
    Write-Output "Cannot run integration tests without newman installed\n Run npm install -g newman to install"
    exit 1
}

$mypath = $MyInvocation.MyCommand.Path | Split-Path -Parent

Clear_DB
$collection = Get-ChildItem "$mypath" -Filter "*environment.json"
Get-ChildItem "$mypath" -Filter "*collection.json" |
ForEach-Object {
    Write-Output $_.FullName
    newman run $_.FullName --bail -e $collection.FullName
    if ( $LASTEXITCODE ) {
        Write-Output "failed collection $_"
        exit 1
    }
}