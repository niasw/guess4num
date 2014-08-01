#Build script for Android with proguard

#cd into the home dir
export PROJECTHOME=~/projects/guess4num
cd $PROJECTHOME

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
cd $PROJECTHOME

#Now into build dir
cd build/classes/

# Create a normal jar
jar -v ../guess4num.jar niasw

# And now use proguard
cd $PROJECTHOME
proguard @proguard.cfg

#Convert to dex format (--no-strict not required)
echo Now convert to dex format
dx --dex --verbose --output=./build/guess4num.dex ./build/guess4num.pro.jar

#Back out
cd $PROJECTHOME

#And finally - create the .apk
apkbuilder ./dist/guess4num.apk -v -u -z ./build/resources.res -f ./build/guess4num.dex

#And now sign it
cd dist
signer guess4num.apk guess4num_signed.apk

cd $PROJECTHOME
