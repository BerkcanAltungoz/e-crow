import './App.css';
import {useDispatch} from "react-redux";
import {useLayoutEffect} from "react";
import Dashboard from "./layouts/Dashboard";

function App() {
  const dispatch = useDispatch();

  useLayoutEffect(() => {
    if(localStorage.getItem("user")){
      dispatch(userLogin(JSON.parse(localStorage.getItem("user"))))
    }
  })

  return (
      <div className="App">
        <Dashboard/>
      </div>
  );
}

export default App;
