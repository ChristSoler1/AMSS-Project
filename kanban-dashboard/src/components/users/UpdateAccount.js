import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";
import {API_URL} from "../../App";
import Header from "../common/Header";

const UpdateAccount = () => {

    const navigate = useNavigate()
    let userId = sessionStorage.getItem("userId")

    const [formData, setFormData] = useState({
        userId: userId,
        email: '',
        password: '',
        username: '',
        phone: '',
        calenderUrl: '',
    });

    const handleChange = (e) => {
        const {name, value, type} = e.target;
        setFormData({...formData, [name]: value});
    };


    useEffect(() => {

        fetch(API_URL + "/v1/kb/users/get/" + userId, {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setFormData({
                userId: userId,
                email: resp.content.email || '',
                password: '',
                username: resp.content.username || '',
                phone: resp.content.phone || '',
                calenderUrl: resp.content.calenderUrl || '',
            });
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });
    }, [])


    const handlesubmit = (e) => {
        e.preventDefault();
        fetch(API_URL + "/v1/kb/users/update", {
            method: "PUT",
            headers: {'content-type': 'application/json'},
            body: JSON.stringify(formData)
        }).then(res => {
            return res.json();
        }).then((resp) => {
            if (resp.responseCode !== "200 OK") {
                console.log(resp.responseMsg);
            } else {
                sessionStorage.setItem('calenderUrl',formData.calenderUrl);
                navigate('/');
            }
        }).catch((err) => {
            console.log('Failed :' + err.message);
        });

    }

    return (
        <div>
            <Header/>
            <div className="row">
                <div className="offset-lg-3 col-lg-6" style={{marginTop: '100px', opacity: 0.8}}>
                    <form className="container" onSubmit={handlesubmit}>
                        <div className="card bg-dark">
                            <div className="card-header text-center text-bg-light">
                                Update User Account
                            </div>
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-lg-6">
                                        <div htmlFortmlFor="email" className="form-group">
                                            <label style={{color:"white"}}>E-Mail<span className="errmsg">*</span></label>
                                            <input disabled={true}
                                                   id="email"
                                                   name="email"
                                                   value={formData.email}
                                                   onChange={handleChange}
                                                   className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>New Passwort</label>
                                            <input
                                                id="password"
                                                name="password"
                                                value={formData.password}
                                                onChange={handleChange}
                                                alt="sda"
                                                type="password"
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>Benutzername<span className="errmsg">*</span></label>
                                            <input disabled={true}
                                                   id="username"
                                                   name="username"
                                                   value={formData.username}
                                                   onChange={handleChange}
                                                   className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>Handynummer</label>
                                            <input
                                                id="phone"
                                                name="phone"
                                                value={formData.phone}
                                                onChange={handleChange}
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="mb-3">

                                        <div className="form-group">
                                            <label style={{color:"white"}}>Kalender URL</label>
                                            <input
                                                id="calenderUrl"
                                                name="calenderUrl"
                                                value={formData.calenderUrl}
                                                onChange={handleChange}
                                                className="form-control"
                                            />
                                        </div>

                                    </div>

                                </div>
                            </div>
                            <div className="card-footer text-center">
                                <div className="text-center">
                                    <button type="submit" className="btn btn-success" style={{width: "100px"}}>
                                        Update
                                    </button>
                                </div>
                                <div className="text-end">
                                    <Link style={{color:"white"}} to={'/login'}>logout</Link>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default UpdateAccount;
