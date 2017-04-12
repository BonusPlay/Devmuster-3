package hobbajt.com.helpme.Base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


import java.io.Serializable;

import hobbajt.com.helpme.Base.BaseFragment;
import hobbajt.com.helpme.Menu.MenuFragment;
import hobbajt.com.helpme.R;

public class FragmentsManager
{
    private final FragmentManager fragmentManager;

    public FragmentsManager(FragmentManager fragmentManager)
    {
        this.fragmentManager = fragmentManager;
    }

    public void showFirstFragment()
    {
        MenuFragment fragment = new MenuFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(fragment.getClass().getSimpleName());

        if(getCurrentFragment() != null)
            transaction.remove(getCurrentFragment());
        transaction.add(R.id.fragment, createFragment(null, fragment));
        transaction.commit();
    }

    public void changeFragment(BaseFragment fragment, Serializable item)
    {
        if (!fragment.getClass().getSimpleName().equals(getCurrentFragment().getClass().getSimpleName()))
        {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(fragment.getClass().getSimpleName());
            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            transaction.replace(R.id.fragment, createFragment(item, fragment));
            transaction.commit();
        }
    }

    private BaseFragment createFragment(Serializable items, BaseFragment fragment)
    {
        fragment.saveState(items);
        return fragment;
    }

    public BaseFragment getCurrentFragment()
    {
        return (BaseFragment) fragmentManager.findFragmentById(R.id.fragment);
    }

    public void backToPreviousFragment()
    {
        fragmentManager.popBackStack();
    }

    public void backToFragment(String name)
    {
        fragmentManager.popBackStackImmediate(name, 0);
    }
}
