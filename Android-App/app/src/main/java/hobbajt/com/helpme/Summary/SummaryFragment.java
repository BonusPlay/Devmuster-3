package hobbajt.com.helpme.Summary;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.R;

public class SummaryFragment extends BaseFragment implements SummaryMVP.View
{
    @BindView(R.id.ivPhone) ImageView ivPhone;
    @BindView(R.id.bExit) Button bExit;

    private SummaryMVP.Presenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);
        ButterKnife.bind(this, view);

        presenter = new SummaryPresenter(getContext(), this, activity.getFragmentsManager());
        presenter.restoreData(getArguments());

        ivPhone.setOnClickListener(view1 ->
        {
            ivPhone.setColorFilter(ContextCompat.getColor(getContext(), R.color.orange), PorterDuff.Mode.SRC_IN);
            Animation animation = android.view.animation.AnimationUtils.loadAnimation(view.getContext(), R.anim.item_shock);
            ivPhone.startAnimation(animation);
            presenter.sendCallRequest();
        });

        bExit.setOnClickListener(view1 -> System.exit(0));

        return view;
    }

    @Override
    public void stopAnimation()
    {
        ivPhone.clearAnimation();
        ivPhone.setColorFilter(ContextCompat.getColor(getContext(), R.color.green), PorterDuff.Mode.SRC_IN);

    }
}
