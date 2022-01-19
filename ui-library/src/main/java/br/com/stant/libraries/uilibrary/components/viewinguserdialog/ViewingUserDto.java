package br.com.stant.libraries.uilibrary.components.viewinguserdialog;

/**
 * Created by denisvieira on 04/08/17.
 */

public class ViewingUserDto {

    private final String mUserName;
    private final String mUserPhoto;
    private final String mUserFunction;
    private int mUserWorkedDays = 0;

    public ViewingUserDto(String userName, String userPhoto, String userFunction, int mUserWorkedDays) {
        this.mUserName = userName;
        this.mUserPhoto = userPhoto;
        this.mUserFunction = userFunction;
        this.mUserWorkedDays = mUserWorkedDays;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserPhoto() {
        return mUserPhoto;
    }

    public String getUserFunction() {
        return mUserFunction;
    }

    public boolean hasUserPhoto(){
        if(getUserPhoto() != null)
            return true;

        return false;
    }
    public int getUserWorkedDays(){return mUserWorkedDays;}

    public void sumMoreOneDay(){
        if(mUserWorkedDays < 999){
            mUserWorkedDays = mUserWorkedDays + 1;
        }

    }

    public void removeOneDay(){
        if(mUserWorkedDays >= 1){
            mUserWorkedDays = mUserWorkedDays - 1;
        }

    }

}
