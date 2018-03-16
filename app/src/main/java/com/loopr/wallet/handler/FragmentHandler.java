package com.loopr.wallet.handler;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.loopr.wallet.BuildConfig;
import com.loopr.wallet.R;
import com.loopr.wallet.activity.BaseActivity;
import com.loopr.wallet.fragment.BaseFragment;
import com.loopr.wallet.utils.tools.LogUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by snow on 2018/3/12.
 * Fragment Manager
 */
public class FragmentHandler {
    private boolean debug = BuildConfig.DEBUG;

    private class FragmentInfo {
        private final boolean isSecondLevel;
        public Class<? extends Fragment> fragmentClass;
        public int idContainer;

        public FragmentInfo(Class<? extends Fragment> fragmentClass, int idContainer, boolean isSecondLevel) {
            this.fragmentClass = fragmentClass;
            this.idContainer = idContainer;
            this.isSecondLevel = isSecondLevel;
        }
    }

    private static class NilFragmentHandler extends FragmentHandler {
        public NilFragmentHandler() {
        }

        @Override
        public void addFragment(Class<? extends Fragment> fragmentClass) {
            return;
        }

        @Override
        public Fragment getCurrentFragment() {
            return null;
        }

        @Override
        public BaseFragment getFragment(Class<? extends Fragment> fragmentClass) {
            return null;
        }

        @Override
        public boolean isFragmentExist(Class<? extends Fragment> fragmentClass) {
            return false;
        }

        @Override
        public boolean registerFragment(Class<? extends Fragment> fragmentClass, int idContainer, boolean isSecondLevel) {
            return false;
        }

        @Override
        public boolean switchToFragment(Class<? extends Fragment> fragmentClass,
                                        boolean addToBackStack, BaseActivity activity) {
            return false;
        }

        @Override
        public boolean switchToFragment(Class<? extends Fragment> fragmentClass,
                                        boolean addToBackStack, int enterAnimation, int exitAnimation,
                                        int popEnterAnimation, int popExitAnimation, BaseActivity activity) {
            return false;
        }

        @Override
        public boolean unregisterFragment(Class<? extends Fragment> fragmentClass) {
            return false;
        }
    }

    public static final NilFragmentHandler NULL_HANDLER = new NilFragmentHandler();

    private Map<String, FragmentInfo> mapFragmentInfo = new HashMap<String, FragmentInfo>();
    private FragmentManager fragmentManager;

    public FragmentHandler() {
    }

    public FragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    /**
     * @param fragmentClass
     * @param idContainer
     * @param isSecondLevel
     * @return
     */
    public boolean registerFragment(Class<? extends Fragment> fragmentClass, int idContainer, boolean isSecondLevel) {
        if (fragmentClass == null)
            return false;

        if (mapFragmentInfo.containsKey(fragmentClass.getName()))
            return false;

        mapFragmentInfo.put(fragmentClass.getName(), new FragmentInfo(fragmentClass, idContainer, isSecondLevel));
        return true;
    }


    public boolean unregisterFragment(Class<? extends Fragment> fragmentClass) {
        if (fragmentClass == null)
            return false;

        if (!mapFragmentInfo.containsKey(fragmentClass.getName()))
            return false;

        mapFragmentInfo.remove(fragmentClass.getName());
        return true;
    }

    public boolean switchToFragment(Class<? extends Fragment> fragmentClass,
                                    boolean addToBackStack, BaseActivity activity) {
        return switchToFragment(fragmentClass, addToBackStack, -1, -1, -1, -1, activity);
    }

    /**
     * switch Fragment with no recycle
     */
    public synchronized boolean switchToFragment(Class<? extends Fragment> fragmentClass,
                                                 boolean addToBackStack, int enterAnimation, int exitAnimation, int popEnterAnimation,
                                                 int popExitAnimation, BaseActivity activity) {

        LogUtils.e("switch", "switchToFragment begin...");
        if (fragmentClass == null) {
            if (debug) LogUtils.d("switch", "fragmentClass==null");
            return false;
        }
        if (debug)
            LogUtils.d("switch", "switchToFragment " + fragmentClass.getName() + " " + addToBackStack);
        if (!mapFragmentInfo.containsKey(fragmentClass.getName())) {
            if (debug)
                LogUtils.d("switch", "!mapFragmentInfo.containsKey(fragmentClass.getName())");
            return false;
        }
        FragmentInfo info = mapFragmentInfo.get(fragmentClass.getName());
        if (info == null || info.fragmentClass == null) {
            if (debug) LogUtils.d("switch", "info==null || info.fragmentClass==null");
            return false;
        }

        List<Fragment> fragments = fragmentManager.getFragments();
        Fragment mShowFragment = null;
        if (fragments != null) {
            for (Fragment iter : fragments) {
                if (!mapFragmentInfo.containsKey(iter.getClass().getName())) {
                    continue;
                }
                if (iter == null || iter.isHidden())
                    continue;
                //no need when ViewPager is gone
                /*FragmentInfo iterInfo = mapFragmentInfo.get(iter.getClass().getName());
                if (iterInfo != null && iterInfo.idContainer != R.id.fragment_tab_container) {
                    mShowFragment = iter;
                }*/
                mShowFragment = iter;
            }
        }

        if (debug) LogUtils.e("switch", "real show fragment: " + fragmentClass.getName());

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass.getName());
        if (debug && fragment != null)
            LogUtils.e("switch", "real show fragment hidden: " + fragment.isHidden());
        if (fragment == null) {
            try {
                /*if (info.idContainer != R.id.fragment_tab_container) {  // fragment_tab_container由ViewPager接管
                    fragment = info.fragmentClass.getConstructor().newInstance();
                    if (debug) LogUtils.d("switch", "Not found fragment " + fragmentClass.getName()
                            + ", create new instance (+" + fragment.hashCode() + ")!");
                    fragmentManager.beginTransaction()
                            .add(info.idContainer, fragment, fragmentClass.getName())
                            .commitAllowingStateLoss();
                }*/
                fragment = info.fragmentClass.getConstructor().newInstance();
                if (debug) LogUtils.d("switch", "Not found fragment " + fragmentClass.getName()
                        + ", create new instance (+" + fragment.hashCode() + ")!");
                fragmentManager.beginTransaction()
                        .add(info.idContainer, fragment, fragmentClass.getName())
                        .commitAllowingStateLoss();
            } catch (Exception e) {
                return false;
            }
        }


        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (enterAnimation != -1 && exitAnimation != -1) {
            if (popEnterAnimation == -1 || popExitAnimation == -1) {
                ft.setCustomAnimations(enterAnimation, exitAnimation);
            } else {
                ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation,
                        popExitAnimation);
            }
        }


        if (mShowFragment != null) {
            ft.hide(mShowFragment);
        }
        ft.show(fragment);
        if (addToBackStack) {
            ft.addToBackStack(fragment.getTag());
        }

        ft.commitAllowingStateLoss();
        if (fragment != null && fragment instanceof BaseFragment) {
            BaseFragment bfg = (BaseFragment) fragment;
            bfg.onFragmentSwitched(activity);
        }
        LogUtils.d("switch", "end!");
        return true;
    }

    public void popBackStackTop() {
        fragmentManager.popBackStack(null, 0);
    }

    public void popBackStackAll() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void popBackStackUnInclusive(Class<? extends Fragment> fragmentClass) {
        fragmentManager.popBackStack(fragmentClass.getName(), 0);
    }

    public void popBackStackInclusive(Class<? extends Fragment> fragmentClass) {
        fragmentManager.popBackStack(fragmentClass.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public Fragment getCurrentFragment() {
        List<Fragment> fragments = fragmentManager.getFragments();
        Fragment homePagerFragment = null;
        if (fragments != null) {
            for (Fragment iter : fragments) {
                if (iter == null || iter.isHidden())
                    continue;
                if (!mapFragmentInfo.containsKey(iter.getClass().getName()))
                    continue;
                FragmentInfo info = mapFragmentInfo.get(iter.getClass().getName());
                if (info != null && info.idContainer == R.id.fragment_tab_container) {
                    if (iter.getUserVisibleHint())
                        homePagerFragment = iter;
                } else
                    return iter;
            }
            return homePagerFragment;
        }
        return null;
    }

    public void addFragment(Class<? extends Fragment> fragmentClass) {
        LogUtils.d("switch", "addFragment begin...");
        if (fragmentClass == null)
            return;

        if (!mapFragmentInfo.containsKey(fragmentClass.getName())) {
            LogUtils.d("switch", "!mapFragmentInfo.containsKey(fragmentClass.getName())");
            return;
        }

        FragmentInfo info = mapFragmentInfo.get(fragmentClass.getName());
        if (info == null || info.fragmentClass == null) {
            LogUtils.d("switch", "info==null || info.fragmentClass==null");
            return;
        }

        Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass.getName());
        if (fragment == null) {
            try {
                fragment = info.fragmentClass.getConstructor().newInstance();
                LogUtils.d("switch", "Not found fragment " + fragmentClass.getName()
                        + ", create new instance (+" + fragment.hashCode() + ")!");
                fragmentManager.beginTransaction()
                        .add(info.idContainer, fragment, fragmentClass.getName()).hide(fragment)
                        .commitAllowingStateLoss();
            } catch (Exception e) {
            }
        }
    }

    public boolean isFragmentExist(Class<? extends Fragment> fragmentClass) {
        return fragmentManager.findFragmentByTag(fragmentClass.getName()) != null;
    }

    public BaseFragment getFragment(Class<? extends Fragment> fragmentClass) {
        if (debug) LogUtils.e("switch", "getFragment " + fragmentClass.getSimpleName());
        if (!mapFragmentInfo.containsKey(fragmentClass.getName())) {
            return null;
        }
        BaseFragment baseFragment = null;
        FragmentInfo info = mapFragmentInfo.get(fragmentClass.getName());
        if (info.idContainer != R.id.fragment_tab_container) {
            baseFragment = (BaseFragment) fragmentManager.findFragmentByTag(fragmentClass.getName());
            if (baseFragment == null) {
                try {
                    if (debug) LogUtils.e("switch", "getFragment newInstance");
                    baseFragment = (BaseFragment) fragmentClass.getConstructor().newInstance();
                    if (debug) {
                        LogUtils.d("FragmentHandler getFragment", "Not found fragment " + fragmentClass.getName()
                                + ", create new instance (+" + baseFragment.hashCode() + ")!");
                    }
                } catch (Exception e) {
                    return baseFragment;
                }
            }
        } else {
            List<Fragment> fragments = fragmentManager.getFragments();
            for (Fragment fragment : fragments) {
                if (fragment.getClass().getName().equals(fragmentClass.getName()) && (fragment instanceof BaseFragment)) {
                    baseFragment = (BaseFragment) fragment;
                    if (debug) LogUtils.e("switch", "found it!");
                    break;
                }
            }
        }

        return baseFragment;
    }
}
