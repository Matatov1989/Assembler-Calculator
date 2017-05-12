package com.yurka.sergeant_matatov.assemblercalculator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by Yurka on 23.02.2017.
 */

public class ImageAdapter extends BaseAdapter {

    // use the default gallery background image
    int defaultItemBackground;
    // gallery context
    private Context galleryContext;
    // array to store bitmaps to display
    private Bitmap[] imageBitmaps;

    public ImageAdapter(Context сontext) {
        // instantiate context
        galleryContext = сontext;
        // create bitmap array
        imageBitmaps = new Bitmap[4];
        // decode the placeholder image
        imageBitmaps[0] = BitmapFactory.decodeResource(galleryContext.getResources(), R.drawable.title_xor);
        imageBitmaps[1] = BitmapFactory.decodeResource(galleryContext.getResources(), R.drawable.title_or);
        imageBitmaps[2] = BitmapFactory.decodeResource(galleryContext.getResources(), R.drawable.title_and);
        imageBitmaps[3] = BitmapFactory.decodeResource(galleryContext.getResources(), R.drawable.title_not);

        // get the styling attributes - use default Andorid system resources
        TypedArray styleAttrs = galleryContext
                .obtainStyledAttributes(R.styleable.PicGallery);
        // get the background resource
        defaultItemBackground = styleAttrs.getResourceId(
                R.styleable.PicGallery_android_galleryItemBackground, 0);
        // recycle attributes
        styleAttrs.recycle();
    }

    // return number of data items i.e. bitmap images
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageBitmaps.length;
    }

    // return item at specified position
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    // return item ID at specified position
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    // get view specifies layout and display options for each thumbnail in the
    // gallery
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // create the view
        ImageView imageView = new ImageView(galleryContext);
        // specify the bitmap at this position in the array
        imageView.setImageBitmap(imageBitmaps[position]);
        // set layout options
        imageView.setLayoutParams(new Gallery.LayoutParams(200, 200));
        // scale type within view area
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        // set default gallery item background
        imageView.setBackgroundResource(defaultItemBackground);
        // return the view
        return imageView;
    }

    // custom methods for this app

    // helper method to add a bitmap to the gallery when the user chooses one
    public void addPic(Bitmap newPic) {
        // set at currently selected index
        imageBitmaps[MainActivity.currentPic] = newPic;
    }

    // return bitmap at specified position for larger display
    public Bitmap getPic(int posn) {
        // return bitmap at posn index
        return imageBitmaps[posn];
    }
}