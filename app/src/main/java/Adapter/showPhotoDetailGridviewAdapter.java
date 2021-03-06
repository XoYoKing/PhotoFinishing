package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.gxl.photofinishing.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import data.needMoveFile;
import customview.MyImageview;

/**
 * Created by gxl on 2016/4/15.
 */
public class showPhotoDetailGridviewAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> listfilepath = new ArrayList<String>();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;


    public showPhotoDetailGridviewAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.listfilepath = list;
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.yujiazai)
                .showImageForEmptyUri(R.drawable.yujiazai)
                .showImageOnFail(R.drawable.yujiazai).cacheInMemory()
                .cacheOnDisc().displayer(new RoundedBitmapDisplayer(20))
                .displayer(new FadeInBitmapDisplayer(300)).build();
    }

    @Override
    public int getCount() {

        return listfilepath.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listfilepath.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Viewholder viewholder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.showphotodetail_item,
                    null);
            convertView = view;
            viewholder = new Viewholder();
            viewholder.imageview = (MyImageview) view
                    .findViewById(R.id.imageview);
            viewholder.ImageView_tip = (ImageView) view.findViewById(R.id.ImageView_tip);
            convertView.setTag(viewholder);
        } else {
            view = convertView;
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.ImageView_tip.setId(position);
        if (needMoveFile.isinNeeddeletePhoto(listfilepath.get(position))) {
            viewholder.ImageView_tip.setVisibility(View.VISIBLE);
        } else {
            viewholder.ImageView_tip.setVisibility(View.GONE);
        }
        imageLoader.displayImage("file:///" + listfilepath.get(position), viewholder.imageview,
                options);
        return view;
    }

    public List<String> getListfilename() {
        return listfilepath;
    }

    class Viewholder {
        ImageView imageview;
        ImageView ImageView_tip;
    }
}
