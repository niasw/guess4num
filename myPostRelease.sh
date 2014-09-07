#!/bin/bash
# niasw's post release process.
jarsigner -keystore ../niaswSignKey/niasw.keystore -digestalg SHA1 -sigalg MD5withRSA -signedjar bin/guess4num-signed.apk bin/guess4num-unsign.apk niasw;
if [ -f "android/bin/TeX2img.apk" ]; then
  rm bin/guess4num.apk;
fi
zipalign -v 4 bin/guess4num-signed.apk bin/guess4num.apk;
# adb install bin/guess4num.apk
# adb logcat | less
