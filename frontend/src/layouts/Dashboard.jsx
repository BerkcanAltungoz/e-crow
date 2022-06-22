import {Route} from "react-router-dom";
import {Container} from "semantic-ui-react";
import Navi from "./Navi";
import MainPage from "../pages/MainPage";
import CustomerSignup from "../pages/CustomerSignup";
import EmployeeSignup from "../pages/EmployeeSignup";
import CustomerLogin from "../pages/CustomerLogin";
import EmployeeLogin from "../pages/EmployeeLogin";
import {ToastContainer} from "react-toastify";

export default function Dashboard() {
    return (
        <div>
            <ToastContainer position="bottom-right" pauseOnFocusLoss={false}/>
            <Container className="main">
                <Navi/>
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/signup/customer" component={CustomerSignup}/>
                <Route exact path="/signup/employee" component={EmployeeSignup}/>
                <Route exact path="/login/customer" component={CustomerLogin}/>
                <Route exact path="/login/employee" component={EmployeeLogin}/>
            </Container>
        </div>
    )
}