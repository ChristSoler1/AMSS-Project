import {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {API_URL} from "../../App";

const Login = () => {
    const [email, emailupdate] = useState('');
    const [password, passwordupdate] = useState('');

    const usenavigate = useNavigate();

    useEffect(() => {
        sessionStorage.clear();
    }, []);

    const ProceedLogin = (e) => {
        e.preventDefault();
        if (validate()) {
            const data = {
                email: email,
                password: password,
            };
            fetch(API_URL + "/v1/kb/users/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            }).then((res) => {
                return res.json();
            }).then((resp) => {
                if (resp.responseCode !== "200 OK") {
                    console.log('Please Enter valid Email or Password');
                } else {
                    sessionStorage.setItem('userId', resp.content.userId);
                    sessionStorage.setItem('email', resp.content.email);
                    sessionStorage.setItem('username', resp.content.username);
                    if (resp.content.calenderUrl === null) {
                        sessionStorage.setItem('calenderUrl', '');
                    }else{
                        sessionStorage.setItem('calenderUrl', resp.content.calenderUrl);
                    }
                    console.log(resp.content.calenderUrl)
                    usenavigate('/')
                }
            }).catch((err) => {
                console.log('Login Failed due to :' + err.message);
            });
        }
    }

    const validate = () => {
        let result = true;
        if (email === '' || email === null) {
            result = false;
            console.log('Please Enter Email');
        }
        if (password === '' || password === null) {
            result = false;
            console.log('Please Enter Password');
        }
        return result;
    }
    return (
        <div style={{marginTop: "100px"}}>
            <div className="row">
                <div className="offset-lg-3 col-lg-6" style={{marginTop: '100px', opacity: 0.8}}>
                    <form onSubmit={ProceedLogin} className="container">
                        <div className="card bg-dark">
                            <div className="card-header text-center text-bg-light">
                                Login
                            </div>
                            <div className="card-body">
                                <div className="form-group">
                                    <label style={{color: "white"}}>E-Mail <span className="errmsg">*</span></label>
                                    <input value={email} onChange={e => emailupdate(e.target.value)}
                                           className="form-control"></input>
                                </div>
                                <div className="form-group">
                                    <label style={{color: "white"}}>Passwort <span className="errmsg">*</span></label>
                                    <input type="password" value={password}
                                           onChange={e => passwordupdate(e.target.value)}
                                           className="form-control"></input>
                                </div>
                            </div>
                            <div className="card-footer">
                                <div className="text-center">
                                    <button type="submit" className="btn btn-primary" style={{width: "100px"}}>Login
                                    </button>
                                </div>
                                <div className="text-end">
                                    <Link style={{color: "white"}} to={'/create'}>Create an Account</Link>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
