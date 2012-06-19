package com.sporksoft.slidepuzzle;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;

public class PuzzlePreferenceActivity extends PreferenceActivity {
	final static int REQUEST_CODE_LOAD_IMAGE = 1;
	
	final static int MENU_RESTORE_DEFAULTS = 0;
	
	// Symbolic names for the keys used for preference lookup
	public static final String SOUND_ON = "pref_key_sound_on";
	public static final String SHOW_STATUS = "pref_key_show_statusbar";
    public static final String BLANK_LOCATION = "pref_key_blank";
    public static final String PUZZLE_SIZE = "pref_key_size";
    public static final String RANDOM_PUZZLE_IMAGE = "pref_key_random_image";
    public static final String CUSTOM_PUZZLE_IMAGE = "pref_key_image";
    public static final String SHOW_IMAGE = "pref_key_show_image";
    public static final String IMAGE_SOURCE = "pref_key_image_source";
    public static final String USE_CUSTOM_IMAGE = "pref_key_custom_image";
    public static final String SHOW_NUMBERS = "pref_key_show_numbers";
    public static final String SHOW_BORDERS = "pref_key_show_borders";
    public static final String SHOW_TIMER = "pref_key_show_timer";
    public static final String NUMBER_COLOR = "pref_key_number_color";
    public static final String BORDER_COLOR = "pref_key_border_color";
    public static final String TIMER_COLOR = "pref_key_timer_color";
    public static final String BACKGROUND_COLOR = "pref_key_bg_color";
    public static final String NUMBER_SIZE = "pref_key_number_size";
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle); 
        addPreferencesFromResource(R.xml.preferences);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch(requestCode) {
            case REQUEST_CODE_LOAD_IMAGE: {
                if (data != null) {
                    ((SelectImagePreference) findPreference(IMAGE_SOURCE)).setCustomLocation(data.getData());                        
                }
            }
            break;
        }
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        
        menu.add(0, MENU_RESTORE_DEFAULTS, 0, R.string.menu_restore_defaults).setIcon(
                R.drawable.ic_menu_preferences);
        
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_RESTORE_DEFAULTS:
                restoreDefaultPreferences();
                return true;
        }
        return false;
    }
    
    private void restoreDefaultPreferences() {
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().clear().commit();
        setPreferenceScreen(null);
        addPreferencesFromResource(R.xml.preferences);
    }
}
