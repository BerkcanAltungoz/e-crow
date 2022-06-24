export const CUSTOMER_LOGIN = "CUSTOMER_LOGIN"
export const EMPLOYEE_LOGIN = "EMPLOYEE_LOGIN"
export const SIGN_OUT = "SIGN_OUT"

export function customerLogin(user) {
    return {
        type: CUSTOMER_LOGIN,
        payload: user
    }
}

export function employeeLogin(user) {
    return {
        type: EMPLOYEE_LOGIN,
        payload: user
    }
}

export function userSignOut() {
    return {
        type : SIGN_OUT
    }
}
