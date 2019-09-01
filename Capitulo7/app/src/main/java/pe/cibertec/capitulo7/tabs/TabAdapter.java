package pe.cibertec.capitulo7.tabs;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabAdapter extends FragmentPagerAdapter {

    public static final String[] TITULOS = {
            "Tab 1", "Tab 2", "Tab 3", "Tab 4"
    };

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ContenidoFragment.newInstance(TITULOS[position]);
    }

    @Override
    public int getCount() {
        return TITULOS.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TITULOS[position];
    }
}
