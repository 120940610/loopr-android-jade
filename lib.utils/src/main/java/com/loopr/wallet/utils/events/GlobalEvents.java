package com.loopr.wallet.utils.events;

import android.support.v4.app.Fragment;

/**
 * Global Events used in EventBus
 *
 * @author snow
 */
public class GlobalEvents {

    public static class GoBack {
    }

    //Fragment Switch
    public static class SwitchToFragment {
        public Class<? extends Fragment> fragmentClass;
        public boolean addToBackStack;
        public int enterAnimation;
        public int exitAnimation;
        public int popEnterAnimation;
        public int popExitAnimation;

        public SwitchToFragment(Class<? extends Fragment> fragmentClass, boolean addToBackStack) {
            this(fragmentClass, addToBackStack, -1, -1, -1, -1);
        }

        public SwitchToFragment(Class<? extends Fragment> fragmentClass, boolean addToBackStack, int enterAnimation, int exitAnimation) {
            this(fragmentClass, addToBackStack, enterAnimation, exitAnimation, -1, -1);
        }

        public SwitchToFragment(Class<? extends Fragment> fragmentClass, boolean addToBackStack,
                                int enterAnimation, int exitAnimation, int popEnterAnimation, int popExitAnimation) {
            this.fragmentClass = fragmentClass;
            this.addToBackStack = addToBackStack;
            this.enterAnimation = enterAnimation;
            this.exitAnimation = exitAnimation;
            this.popEnterAnimation = popEnterAnimation;
            this.popExitAnimation = popExitAnimation;
        }
    }

    public static class ShowMainTab {
    }
}
