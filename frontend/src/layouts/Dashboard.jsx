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
import CustomerTransactions from "../pages/CustomerTransactions";
import CustomerAccount from "../pages/CustomerAccount";
import EmployeeTransactions from "../pages/EmployeeTransactions";
import EmployeeAccount from "../pages/EmployeeAccount";
import CustomerAddress from "../pages/CustomerAddress";
import CustomerPayment from "../pages/CustomerPayment";
import CustomerBankAccount from "../pages/CustomerBankAccount";
import EmployeeDetails from "../pages/EmployeeDetails";
import EmployeeBankAccount from "../pages/EmployeeBankAccount";

export default function Dashboard() {
    return (
        <div>
            <Navi/>
            <ToastContainer position="bottom-right" pauseOnFocusLoss={false}/>
            <Container className="main">
                <Route exact path="/" component={MainPage}/>
                <Route exact path="/messages" component={Messages}/>

                <Route exact path="/signup/customer" component={CustomerSignup}/>
                <Route exact path="/login/customer" component={CustomerLogin}/>
                <Route exact path="/customer/transactions" component={CustomerTransactions}/>
                <Route exact path="/customer/account" component={CustomerAccount}/>
                <Route exact path="/customer/address" component={CustomerAddress}/>
                <Route exact path="/customer/payment" component={CustomerPayment}/>
                <Route exact path="/customer/bankAccount" component={CustomerBankAccount}/>


                <Route exact path="/signup/employee" component={EmployeeSignup}/>
                <Route exact path="/login/employee" component={EmployeeLogin}/>
                <Route exact path="/employee/transactions" component={EmployeeTransactions}/>
                <Route exact path="/employee/account" component={EmployeeAccount}/>
                <Route exact path="/employee/details" component={EmployeeDetails}/>
                <Route exact path="/employee/bankAccount" component={EmployeeBankAccount}/>
            </Container>
        </div>
    )
}