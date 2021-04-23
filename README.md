# Optical-Mark-Recognition-Android

OMR Scanner android app made using OpenCV Android SDK capable of recognising simple omr sheet bubbles and generate the result using an answer key.

### Uses OpenCV contour detection and Image Processing to recognise darkened bubbles and prints the recognised answers with 95-100% accuracy.
### Takes an image of the OMR sheets and gives an option to crop it to make it fit the given discription and also to remove background noise.
### Saves it and then scans it using OpenCV and saves the results in a file which can then be checked with an answer key that was added prior to the scan.
### Can save an answer key and also modify existing ones within the app.


<p> <img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/s1.jpeg" width =200 
  height = 350/>
<img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/s3.jpeg" width =200 
  height = 350/>
<img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/s2.jpeg" width =200 
  height = 350/>
<img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/s4.jpeg" width =200 
  height = 350/>
</p>

### Can automatically check the answers with the defined answer key and grade individual scanned sheets.
### Can even scan an OMR directly from the computer screen in case of digital records with a good accuracy as well and provide similar results to a real OMR sheet.
### Can recognise upto 20 question blocks and more.
##
## Uses Otsu's Thresholding method to invert the foreground and background colors to detect filled bubble with ease and less effort.
<p> <img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/otsu_s_thresholding.jpg" width =200 
  height = 350/></p>
 
## This is an example on how it works
![Alt Text](https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/how_threshold_works.gif)

## See the app in action.
## Working of the app on 2 different OMR sheets taken stright from the computer screen.
## https://youtu.be/g2s8hGLgqaw
<p> <img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/sheet_1.jpg" width =200 
  height = 350/>
<img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/demo_1.jpeg" width =200 
  height = 350/></p>
  
## https://youtu.be/6ZmqwAGwBTQ
<p> <img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/sheet_2.jpg" width =200 
  height = 350/>
<img hspace="10" src="https://github.com/KunalFarmah98/Optical-Mark-Recognition-Android/blob/main/app/src/main/res/raw/demo_2.jpeg" width =200 
  height = 350/></p>

## Why not use circle detection?
#### Tuning the parameters to Hough circles on an image-to-image basis is a difficult task.
#### Many times, whether purposely or not, we have filled in outside the lines on your bubble sheet. Circles don’t handle deformations in their outlines very well — the circle detection would totally fail in that case.
#### Because of this, it is recommended using contours and contour properties to help us filter the bubbles and answers and then process them the way we want. 
##
#### More information on the method and algorithm used can be found here: https://www.pyimagesearch.com/2016/10/03/bubble-sheet-multiple-choice-scanner-and-test-grader-using-omr-python-and-opencv/
##
