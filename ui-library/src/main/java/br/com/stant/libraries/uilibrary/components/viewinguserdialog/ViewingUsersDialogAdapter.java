package br.com.stant.libraries.uilibrary.components.viewinguserdialog;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.stant.libraries.uilibrary.R;
import br.com.stant.libraries.uilibrary.databinding.ViewingUsersWorkedDaysDialogItemBinding;

/**
 * Created by Gabe on 25/09/2016.
 */
public class ViewingUsersDialogAdapter extends
        RecyclerView.Adapter<ViewingUsersDialogAdapter.ItemViewHolder>{

    private ItemViewHolder mViewHolder;
    private List<ViewingUserDto> mTeamMembers;
    private Context mContext;
    private ViewingUsersDialogViewContract mServiceInspectionFormFilledContract;
    private Boolean mApproved;

    public void replaceData(List<ViewingUserDto> viewingUsersDto) {
        setList(viewingUsersDto);
        notifyDataSetChanged();
    }

    public void approved(Boolean approved){
        this.mApproved = approved;
    }

    private void setList(List<ViewingUserDto> list) {
        mTeamMembers = list;
    }

    public ViewingUsersDialogAdapter(Context context, List<ViewingUserDto> teamMembers, ViewingUsersDialogViewContract viewingUsersDialogViewContract){
        this.mTeamMembers                         = teamMembers;
        this.mContext                             = context;
        this.mServiceInspectionFormFilledContract = viewingUsersDialogViewContract;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        ViewingUsersDialogItemBinding viewingUsersDialogItemBinding =
//                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
//                        R.layout.viewing_users_worked_days_dialog_item,
//                        parent, false);

        ViewingUsersWorkedDaysDialogItemBinding viewingUsersWorkedDaysDialogItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.viewing_users_worked_days_dialog_item, parent, false);

        return new ItemViewHolder(viewingUsersWorkedDaysDialogItemBinding);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        mViewHolder = holder;

        ViewingUserDto teamMember = mTeamMembers.get(position);

        //mViewHolder.mViewingUsersDialogItemBinding.viewingUsersDialogItemUserNameTextView.setText(teamMember.getUserName());
        mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersDialogItemUserNameTextView.setText(teamMember.getUserName());
        //mViewHolder.mViewingUsersDialogItemBinding.viewingUsersDialogItemUserFunctionTextView.setText(teamMember.getUserFunction());
        mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersDialogItemUserFunctionTextView.setText(teamMember.getUserFunction());
        if(teamMember.hasUserPhoto()){
            Picasso.with(mContext)
                    .load(teamMember.getUserPhoto())
                    .resize(180, 180)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_holder)
                    .error(R.drawable.ic_user_holder)
                    .into(mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersDialogItemUserPhotoCircularImageView);
                    //.into(mViewHolder.mViewingUsersDialogItemBinding.viewingUsersDialogItemUserPhotoCircularImageView);
        }

        if(mApproved){
            mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonLess.setEnabled(false);
            mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonMost.setEnabled(false);
        }

        int userWorkedDays = teamMember.getUserWorkedDays();

        mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonLess.setOnClickListener(v -> {

            teamMember.removeOneDay();

            mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersDialogItemUserValue.setText(teamMember.getUserWorkedDays());

            if(userWorkedDays == 999){
                mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonMost.setEnabled(false);
            }
        });

        mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonMost.setOnClickListener(v -> {

            teamMember.sumMoreOneDay();

            mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersDialogItemUserValue.setText(teamMember.getUserWorkedDays());

            if(userWorkedDays == 0){
                mViewHolder.viewingUsersWorkedDaysDialogItemBinding.viewingUsersWorkedDaysDialogItemButtonLess.setEnabled(false);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mTeamMembers.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        //private ViewingUsersDialogItemBinding mViewingUsersDialogItemBinding;
        private ViewingUsersWorkedDaysDialogItemBinding viewingUsersWorkedDaysDialogItemBinding;


        public ItemViewHolder(ViewingUsersWorkedDaysDialogItemBinding mServiceInspectionFormFilledDetailTeamDialogItemBinding) {
            super(mServiceInspectionFormFilledDetailTeamDialogItemBinding.getRoot());

            //this.mViewingUsersDialogItemBinding = mServiceInspectionFormFilledDetailTeamDialogItemBinding;
            this.viewingUsersWorkedDaysDialogItemBinding = mServiceInspectionFormFilledDetailTeamDialogItemBinding;
        }

    }

}
