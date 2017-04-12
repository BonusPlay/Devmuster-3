package hobbajt.com.helpme.Menu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.R;
import hobbajt.com.helpme.HelpType;
import hobbajt.com.helpme.SharedPrefs.SharedPrefs;
import hobbajt.com.helpme.SharedPrefs.SharedPrefsReader;

class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.NavigationItemHolder>
{
    private final Context context;
    private MenuMVP.Presenter presenter;

    private HelpType[] helpTypes = HelpType.values();

    MenuAdapter(MenuMVP.Presenter presenter, Context context)
    {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public NavigationItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new NavigationItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigationItemHolder holder, int position)
    {
        holder.tvName.setTextColor(Color.WHITE);

        HelpType helpType = helpTypes[position];

        holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, helpType.getIconId()));
        holder.tlItem.setBackgroundColor(helpType.getColor());
        holder.tlItem.setBackgroundColor(ContextCompat.getColor(context, helpType.getColor()));
        holder.tvName.setText(helpType.getName());
        holder.ivIcon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        holder.tlItem.setOnClickListener(view -> presenter.onTileClicked(helpType));


        setScaleAnimation(holder.itemView);
    }

    private void setScaleAnimation(View view)
    {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }

    @Override
    public int getItemCount()
    {
        return helpTypes.length;
    }

    static class NavigationItemHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.tlItem) TileLayout tlItem;
        @BindView(R.id.ivIcon) ImageView ivIcon;
        @BindView(R.id.tvName) TextView tvName;

        NavigationItemHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
