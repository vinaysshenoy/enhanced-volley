# enhanced-volley

An Enhanced version of the Volley Networking Tookit for Android

## License

Copyright (C) 2011 The Android Open Source Project

Copyright (C) 2013 Vinay S Shenoy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Credits
This project is based on the Volley Networking Toolkit for Android created by Google themselves. 
The toolkit itself is really great and extensible, but it is missing a few features. This project 
aims to add those features to the Volley toolkit to make it a more complete HTTP solution.

## Added features
1. Support for custom HTTP Headers
2. Support for custom URL parameters(GET requests only)
3. Default BitmapLruCache implementation added to ImageLoader
4. AnimateImageView which will automatically perform any animations you require.
5. Multipart Requests(File uploads support only application/octet-stream MIME type as of now)

## Planned features
1. A generic OAuth Client implementation
2. A WebSocket & Socket.IO client implementation based on the one found here - https://github.com/koush/android-websockets
