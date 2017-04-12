package hobbajt.com.helpme.Events;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.CustomHelpType;
import hobbajt.com.helpme.Menu.TileLayout;
import hobbajt.com.helpme.R;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventCategoryItemHolder>
{
    private final Context context;
    private EventsMVP.Presenter presenter;

    private List<? extends Object> data;

    EventsAdapter(EventsMVP.Presenter presenter, Context context)
    {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public EventCategoryItemHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new EventCategoryItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(EventCategoryItemHolder holder, int position)
    {
        holder.tvName.setTextColor(Color.WHITE);
        holder.ivIcon.setColorFilter(ContextCompat.getColor(context, android.R.color.white), PorterDuff.Mode.SRC_IN);
        if(data.get(position) instanceof Events)
        {
            Events event = (Events) data.get(position);

            holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, event.getIconId()));
            holder.tvName.setText(event.getName());
            holder.tlItem.setBackgroundColor(ContextCompat.getColor(context, event.getHelpType().getColor()));
            holder.tlItem.setOnClickListener(view -> presenter.onEventClicked(event));
        }
        else if(data.get(position) instanceof CustomHelpType)
        {
            CustomHelpType customHelpType = (CustomHelpType) data.get(position);

            holder.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_person));
            holder.tvName.setText(customHelpType.getName());
            holder.tlItem.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
            holder.tlItem.setOnClickListener(view -> presenter.onCoustomHelpClicked(customHelpType));
        }

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
        return data.size();
    }

    public void setData(List<? extends Object> data)
    {
        this.data = data;
    }

    static class EventCategoryItemHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.tlItem) TileLayout tlItem;
        @BindView(R.id.ivIcon) ImageView ivIcon;
        @BindView(R.id.tvName) TextView tvName;

        EventCategoryItemHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
