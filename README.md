# Indicator-Slider


Example:
#For correct behaviour for relative layout (It's container for indicators) above viewpager 
#you must to wrap these views in FrameLayout for full controll relataive on viewpager


<FrameLayout 
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v4.view.ViewPager
        android:id="@+id/indicator_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom">

        <com.example.vladimir.customslider.custom_view.IndicatorContainer
            android:id="@+id/custom_view"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginBottom="20dp"
            android:gravity="center"
            app:active_color="@android:color/white"
            app:active_item="7dp"
            app:inactive_color="@color/lightGrey"
            app:inactive_item="5dp"
            app:indicators="5"
            app:number_active="2" />
    </FrameLayout>

</FrameLayout>

[![rsz_device-2017-01-01-205017.png](https://s27.postimg.org/51wqsi3hf/rsz_device_2017_01_01_205017.png)](https://postimg.org/image/88rac4nxb/)
