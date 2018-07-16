# Easy Location



### Installing

The `Permission library` is already added in it you can access [here](https://github.com/irshadsparky/PermissionExample) to kow more.
Use the following in your `build.gradle`:

```groovy
use dependency

 maven { url 'https://jitpack.io' }

dependencies {
   implementation ('com.github.irshadsparky:EasyLocation:master-SNAPSHOT') {
        exclude  group:'com.android.support', module:'support-annotations'
        exclude  group:'com.android.support', module:'support-v4'
    }
}
```

### Basic setup and usage


2. The `Easy Location Library` to your Manifest xml:
```xml
<service
            android:name="com.irshad.EasyLocation.service.LocationService"android:launchMode="singleTop" />
     for runtime permission       
  <activity android:name="com.irshad.permissionlib.PermissionUtils$PermissionActivity" />
```
   
 You can modify them as per your requirement.
    
4. Finally, you can use library like :

```java

 ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService mLocationService = ((LocationService.LocationBinder) service).getService();
            mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener() {
                @Override
                public void getLocation(final String lastLatitude, final String lastLongitude, final String latitude, final String longitude, final String country, final String locality, final String street) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvAboutLocation.setText("lastLatitude: " + lastLatitude + "lastLongitude: " + lastLongitude + "latitude: " + latitude + "longitude: " + longitude + "getCountryName: " + country + "getLocality: " + locality + "getStreet: " + street
                            );
                        }
                    });
                }
            });
        }
    };
 @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, LocationService.class), conn, Context.BIND_AUTO_CREATE);

    }
    
 @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
    
```
you can also develope your require permission.
5. Initialize in your Application class.
```java

Utils.init(this);

public class MyApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
```
6. That's it! if any truble download this project and run it you can understand batter

### Contributing

0. Fork this repo and clone your fork
0. Make your desired changes
0. Add tests for your new feature and ensure all tests are passing
0. Commit and push
0. Submit a Pull Request through Github's interface and a project maintainer will
decide your change's fate.

_Note: issues can be submitted via [github issues](https://github.com/irshadsparky/EasyLocationExample/issues/new)_

Developed By
------------
Mohammad Irshad Sheikh - ir9977(at).gmail.com

### License

PlacesAutocomplete is released under a [BSD 2-Clause License](http://opensource.org/licenses/BSD-2-Clause), viewable [here](LICENSE.txt)
