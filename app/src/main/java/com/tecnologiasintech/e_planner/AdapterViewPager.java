package com.tecnologiasintech.e_planner;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sergiosilva on 12/19/17.
 */

class AdapterViewPager extends FragmentPagerAdapter {

    private int mNumberOfTabs;

    public AdapterViewPager(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        /**
         * Get the position from the parameter
         * thanks to the position, we can send the correspondent Fragment to the Section.
         * */
        switch (position) {

            case 0:
                return PurposedEventFragment.newInstance();
            case 1:
                return EventFragment.newInstance();
            case 2:
                return ParticipantFragment.newInstance();
            case 3:
                return MentorFragment.newInstance();

            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {

        /**
         * Get the position from the parameter
         * thanks to the position, we can send the correspondent tab title
         * */
        switch (position) {

            case 0: // The first tab will always initialize at 0.
                return "Propuestos";
            case 1:
                return "Evento";
            case 2:
                return "Participantes";
            case 3:
                return "Mentores";

            // If the position we receive doesn't correspond to any section.
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
