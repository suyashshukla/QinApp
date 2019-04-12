package com.nikaas.suyashshukla.qin;

import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.TransitionSet;

public class AppTransition extends TransitionSet {

    public AppTransition(){
        setOrdering(ORDERING_TOGETHER);

        addTransition(new ChangeBounds())
                .addTransition(new ChangeImageTransform())
                .addTransition(new ChangeTransform());
    }

}
