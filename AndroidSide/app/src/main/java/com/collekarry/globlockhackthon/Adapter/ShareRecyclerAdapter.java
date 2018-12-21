package com.collekarry.globlockhackthon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.collekarry.globlockhackthon.AddPost;
import com.collekarry.globlockhackthon.Model.shareData;
import com.collekarry.globlockhackthon.R;

import java.util.ArrayList;


public class ShareRecyclerAdapter extends RecyclerView.Adapter<ShareRecyclerAdapter.customViewHolder> {

    static ArrayList<ArrayList<String>> arrayLists = shareData.getData(4);
    Context context;
    socialMediaShare share;

    public ShareRecyclerAdapter(Context context , socialMediaShare share){
        this.share = share;
        this.context = context;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.share_card_view , viewGroup , false);

        return new customViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final customViewHolder customViewHolder, int i) {

        String title = arrayLists.get(i).get(0);
        String description = arrayLists.get(i).get(2);
        int imageId = Integer.parseInt(arrayLists.get(i).get(1));

        customViewHolder.getTitleTextView().setText(title);
        Glide.with(context)
                .load(context.getDrawable(imageId))
                .apply(new RequestOptions().override(50, 100))
                .into(customViewHolder.imageView);

        customViewHolder.getDescriptionTextView().setText(description);
        final Bundle bundle = new Bundle();
        bundle.putString(shareData.SHARE_TITLE , title);
        bundle.putString(shareData.SHARE_DESCRIPTION , description);
        bundle.putInt(shareData.SHARE_IMAGE , imageId);

        customViewHolder.getEditImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context , AddPost.class).putExtra("bundle" , bundle));

            }
        });

        customViewHolder.getShareImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context ,customViewHolder.getShareImageView() , Gravity.BOTTOM);
                popupMenu.getMenuInflater().inflate(R.menu.popup_share_menu , popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch(menuItem.getItemId()){
                            case R.id.facebook_share:
                                shareInFacebook(bundle);
                                break;
                            case R.id.whats_app_share:
                                shareInWhatsApp(bundle);
                                break;
                            case R.id.twitter_share:
                                shareInTwitter(bundle);
                                break;
                            case R.id.instagram_share:
                                shareInInstagram(bundle);
                                break;
                                default:
                                    return false;
                        }
                        return true;
                    }
                });

            }
        });
         }

    @Override
    public int getItemCount() {
        return arrayLists.size();
    }

    class customViewHolder extends RecyclerView.ViewHolder{

        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView imageView;
        private ImageView editImageView;
        private ImageView shareImageView;
        private View view;



        public customViewHolder(View view){
            super(view);
            titleTextView = view.findViewById(R.id.title_textView);
            imageView = view.findViewById(R.id.content_image);
            descriptionTextView = view.findViewById(R.id.description_text_view);
            editImageView = view.findViewById(R.id.edit_imageView);
            shareImageView = view.findViewById(R.id.share_imageView);
            this.view = view;
            }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getDescriptionTextView() {
            return descriptionTextView;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public ImageView getEditImageView() {
            return editImageView;
        }

        public ImageView getShareImageView() {
            return shareImageView;
        }

        public View getView() {
            return view;
        }

    }

    private void shareInWhatsApp(Bundle bundle){
share.whatsAppShare(bundle);

    }
    private void shareInInstagram(Bundle bundle){
share.instagramShare(bundle);
    }
    private void shareInTwitter(Bundle bundle){
share.twitterShare(bundle);
    }
    private void shareInFacebook(Bundle bundle){
share.facebookShare(bundle);

    }
    public interface socialMediaShare{
        public void facebookShare(Bundle bundle);
        public void instagramShare(Bundle bundle);
        public void whatsAppShare(Bundle bundle);
        public void twitterShare(Bundle bundle);
    }
}

