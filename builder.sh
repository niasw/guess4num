#Build script

#cd into the home dir - this way it works when run from inside vim or any other folder
cd ~/projects/guess4num/

#Clean up
rm -rf build
rm -rf dist

#create the needed directories
mkdir -m 770 -p dist
mkdir -m 770 -p build/classes

#Rmove the R.java file as will be created by aapt
rm src/niasw/game/guess4num/R.java 

#Now use aapt
echo Create the R.java file
aapt p -f -v -M AndroidManifest.xml -F ./build/resources.res -I ~/system/classes/android.jar -S res/ -J src/niasw/game/guess4num

#cd into the src dir
cd src

#Now compile - note the use of a seperate lib (in non-dex format!)
echo Compile the java code
javac -verbose -d ../build/classes niasw/game/guess4num/MainActivity.java 
javac -verbose -d ../build/classes niasw/game/guess4num/GuessNumberGame.java 

#Back out
cd ..

#Now into build dir
cd build/classes/

#Now convert to dex format (need --no-strict)
echo Now convert to dex format
dx --dex --verbose --no-strict --output=../guess4num.dex niasw

#Back out
cd ../..

#And finally - create the .apk
apkbuilder ./dist/guess4num.apk -v -u -z ./build/resources.res -f ./build/guess4num.dex 

#And now sign it
cd dist
signer guess4num.apk guess4num_signed.apk

cd ..

