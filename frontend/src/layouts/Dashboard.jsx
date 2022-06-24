import {Route} from "react-router-dom";
import {Container} from "semantic-ui-react";
import Navi from "./Navi";
import MainPage from "../pages/MainPage";
import CustomerSignup from "../pages/CustomerSignup";
import EmployeeSignup from "../pages/EmployeeSignup";
import CustomerLogin from "../pages/CustomerLogin";
import EmployeeLogin from "../pages/EmployeeLogin";
import {ToastContainer} from "react-toastify";
import Messages from "../pages/Messages";
import CustomerAccount from "../pages/CustomerAccount";
import Transactions from "../pages/Transactions";
import EmployeeAccount from "../pages/EmployeeAccount";
import CustomerAddress from "../pages/CustomerAddress";
import CustomerPayment from "../pages/CustomerPayment";
import BankAccount from "../pages/BankAccount";
import EmployeeDetails from "../pages/EmployeeDetails";
import Footer from "./Footer";

export default function Dashboard() {
    return (
        <div>
            <Navi/>
            <ToastContainer position="bottom-right" pauseOnFocusLoss={false}/>
            <Container className="main">
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/messages" component={Messages}/>
                <Route exact path="/bankAccount" component={BankAccount}/>
                <Route exact path="/transactions" component={Transactions}/>

                <Route exact path="/signup/customer" component={CustomerSignup}/>
                <Route exact path="/login/customer" component={CustomerLogin}/>
                <Route exact path="/customer/account" component={CustomerAccount}/>
                <Route exact path="/customer/address" component={CustomerAddress}/>
                <Route exact path="/customer/payment" component={CustomerPayment}/>

                <Route exact path="/signup/employee" component={EmployeeSignup}/>
                <Route exact path="/login/employee" component={EmployeeLogin}/>
                <Route exact path="/employee/account" component={EmployeeAccount}/>
                <Route exact path="/employee/details" component={EmployeeDetails}/>
            </Container>
            <Footer/>
        </div>

    )
}