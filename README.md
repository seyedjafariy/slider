# Slider [![](https://jitpack.io/v/worldsnas/slider.svg)](https://jitpack.io/#worldsnas/slider)
This is an Infinite auto sliding Slider library for Android built on top of [Epoxy](https://github.com/airbnb/epoxy). You have all the benefits of Epoxy plus automatic and infinite sliding on top.

Slider is not bound to any particular view so you can use any type of view as long as it's a [EpoxyModel](https://github.com/airbnb/epoxy/wiki/Epoxy-Models).

## Demo

![](assets/slider.gif)

## Usage

### Step 1

Add dependency to your build.gradles

Inside root level `build.gradle`
```Gradle
allprojects {
            repositories {
                        ...
		maven { url 'https://jitpack.io' }
	}
}
```

Inside App `build.gradle`
```Gradle
dependencies {
            implementation 'com.github.worldsnas:slider:{latest_version}'
}
```

### Step 2

Add `Slider` to your epoxy models:

```Kotlin

yourEpoxyView.withModels {
            slider {
                id("your slider id")
                
                //delay before every cycle if user is not scrolling
                cycleDelay(5_000)
                
                //controll the indicator visibility
                indicatorVisible(true)
                //Front/Selected indicator dot color (if indicator visible)
                indicatorSelectedDotColor(Color.YELLOW)
                //rest of indicator dot color (if indicator visible)
                indicatorDotColor(Color.BLACK)
                
                
                models(
                         // list of all the sliding models 
                         // don't forget to add Id for them
                )
            }
        }
```

To add infinite sliding you can use `infinite(true)`. 
The only limitation for infinite scrolling is you need to add a `copier` function to the builder.

The complete `slider` builder with infinite scrolling will look like this:
```Kotlin

yourEpoxyView.withModels {
            slider {
                id("your slider id")
                cycleDelay(5_000)
                
                indicatorVisible(true)
                indicatorSelectedDotColor(Color.YELLOW)
                indicatorDotColor(Color.BLACK)
                
                models(
                         banners.mapIndexed { index, s ->
                            BannerViewModel_().apply {
                                id(index.toLong())
                                bindImage(s)
                                listener {
                                    showImageUrl(it)
                                }
                            }
                        }
                )
                
                infinite(true)
                copier { oldModel ->
                    oldModel as BannerViewModel_
                    //you have to create a new object and return here
                    BannerViewModel_().apply {
                        id(oldModel.id())
                        bindImage(banners[oldModel.id().toInt()])
                        listener { imageUrl ->
                            showImageUrl(imageUrl)
                        }
                    }
                }
            }
        }

```

Now run your application!

For further usage please checkout [SampleApp](/app/src/main/java/com/worldsnas/sliderlibrary/MainActivity.kt)

## Thanks
- [Epoxy](https://github.com/airbnb/epoxy)

## Contribution

There are a lot to be done here and I'm more than happy to discuss so:

- Create issues
- Send PRs

Any contribution is more than wellcome
