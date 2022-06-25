import {Link, useHistory} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {userSignOut} from "../store/actions/userActions";
import {Menu} from 'semantic-ui-react'
import {toast} from "react-toastify";

export default function SignedIn() {

    const userProps = useSelector(state => state?.user?.userProps)
    const dispatch = useDispatch()

    const history = useHistory();

    const handleSignout = (user) => {
        dispatch(userSignOut(user))
        localStorage.removeItem("customer");
        localStorage.removeItem("employee");
        history.push("/")
        toast.error("Signed Out")
    }

    return (

        <div>
            <Menu.Item fitted={"vertically"}>
                {userProps.userType === 1 && <Menu.Item as={Link} to={"/customer/account"}>My Account</Menu.Item>}
                {userProps.userType === 1 && <Menu.Item as={Link} to={"/transactions"}>Transactions</Menu.Item>}
                {userProps.userType === 2 && <Menu.Item as={Link} to={"/employee/account"}>My Account</Menu.Item>}
                {userProps.userType === 2 && <Menu.Item as={Link} to={"/transactions"}>Transactions</Menu.Item>}
                <Menu.Item onClick={() => handleSignout(userProps.user)}>Sign-Out</Menu.Item>
            </Menu.Item>

        </div>
    )
}