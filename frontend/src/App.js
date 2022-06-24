import './App.css';
import Dashboard from "./layouts/Dashboard";
import {useLayoutEffect} from "react";
import {customerLogin, employeeLogin} from "./store/actions/userActions";
import {useDispatch} from "react-redux";

function App() {

  const dispatch = useDispatch();

  useLayoutEffect(() => {
    if(localStorage.getItem("customer")){
      dispatch(customerLogin(JSON.parse(localStorage.getItem("customer"))))
    }

      if(localStorage.getItem("employee")){
          dispatch(employeeLogin(JSON.parse(localStorage.getItem("employee"))))
      }
  })

  return (
      <div className="App">
        <Dashboard/>
      </div>
  );
}

export default App;
