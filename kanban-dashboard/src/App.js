
import './App.css';
import {ToastContainer} from "react-toastify";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home";
import Login from "./components/users/Login";
import CreateAccount from "./components/users/CreateAccount";
import UpdateAccount from "./components/users/UpdateAccount";
import Search from "./components/Search";


// Konstante URL für API
export const API_URL = "http://localhost:8086"

function App() {
  return (
    <div className="App">
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home/>}></Route>
                <Route path="/login" element={<Login/>}></Route>
                <Route path="/create" element={<CreateAccount/>}></Route>
                <Route path="/updateaccount" element={<UpdateAccount/>}></Route>
                <Route path="/search" element={<Search/>}></Route>
            </Routes>
        </BrowserRouter>
    </div>
  );
}


//Export
export default App;
