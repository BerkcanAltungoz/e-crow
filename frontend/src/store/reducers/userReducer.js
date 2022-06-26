import {userProps} from "../initialValues/userProps";
import {CUSTOMER_LOGIN, EMPLOYEE_LOGIN, SIGN_OUT, UPDATE_BALANCE} from "../actions/userActions";

const initialState = {
    userProps : userProps
}

export default function userReducer(state = initialState, {type, payload}){
    switch (type) {
        case CUSTOMER_LOGIN:
            return {
                ...state,
                userProps:{
                    user: payload,
                    userType: 1,
                    loggedIn: true
                }
            }
        case EMPLOYEE_LOGIN:
            return {
                ...state,
                userProps:{
                    user: payload,
                    userType: 2,
                    loggedIn: true
                }
            }
        case SIGN_OUT:
            return {
                ...state,
                userProps: {
                    user: null,
                    userType: 0,
                    loggedIn: false
                }
            }
        case UPDATE_BALANCE:
            return {
                ...state,
                userProps: {
                    ...state.userProps,
                    user: {...state.userProps.user, balance:payload},
                    ...state.userProps.user.balance
                }
            }
        default:
            return state;
    }
}