# react-native-wallpaper
Set Wallpaper with react-native (Android only)
## Install

    npm install --save https://github.com/kaissaroj/react-native-wallpaper.git
## Link
     react-native link react-native-walle
## Usage

    import RNWalle from "react-native-walle";
    
    RNWalle.setWallPaper("http://i.imgur.com/DvpvklR.png", function(res) {
      console.log(res);
      //res : 'success'
      //res : 'failed' or 'reason to fail while trying to set wallpaper'
    });
    
    
<h3>#Feel free to Contribute<h3>


