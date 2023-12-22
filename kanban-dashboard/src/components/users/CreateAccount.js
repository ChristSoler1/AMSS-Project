import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {API_URL} from "../../App";

//Account Erstellung - React Komponente

const CreateAccount = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [username, setUsername] = useState('');
    const [phone, setPhone] = useState('');

    const navigate = useNavigate()


    //Die Funktion validateCreateAccount überprüft, ob email, password und username nicht leer sind.
    // Wenn eine dieser Variablen leer ist, loggt sie eine Fehlermeldung in die Konsole und setzt result auf false
    const validateCreateAccount = () => {
        let result = true;
        if (email === '' || email === null) {
            result = false;
            console.log('Please Enter Email');
        }
        if (password === '' || password === null) {
            result = false;
            console.log('Please Enter Password');
        }

        if (username === '' || username === null) {
            result = false;
            console.log('Please Enter Username');
        }

        return result;
    }

    //Die Funktion handlesubmit wird ausgeführt, wenn das Formular abgeschickt wird. Sie prüft zunächst, ob validateCreateAccount true zurückgibt.
    // Ist das der Fall, wird ein POST-Request an den API-Endpunkt /v1/kb/users/create gesendet, um ein neues Nutzerkonto zu erstellen.
    // Wenn der Request erfolgreich ist und der responseCode "200 OK" ist, wird eine Erfolgsmeldung in die Konsole geloggt und der Nutzer wird zur Homepage umgeleitet.
    // Falls der Request fehlschlägt, wird eine Fehlermeldung in die Konsole geloggt.

    const handlesubmit = (e) => {
        e.preventDefault();
        if (validateCreateAccount()) {
            let regObj = {password: password, email: email, username: username, phone: phone}
            fetch(API_URL + "/v1/kb/users/create", {
                method: "POST",
                headers: {'content-type': 'application/json'},
                body: JSON.stringify(regObj)
            }).then(res => {
                return res.json();
            }).then((resp) => {
                if (resp.responseCode !== "200 OK") {
                    console.log(resp.responseMsg);
                } else {
                    console.log('Account Created successfully.')
                    navigate('/');
                }
            }).catch((err) => {
                console.log('Failed :' + err.message);
            });
        }
    }

    //Der return-Ausdruck in der Komponente gibt ein Formular zurück, das es dem Nutzer ermöglicht, seine Email,
    // sein Passwort, seinen Benutzernamen und seine Handynummer einzugeben.
    // Das Formular enthält außerdem einen Button zum Abschicken der Eingaben und einen Link zur Login-Seite.

    return (
        <div>
            <div className="row">
                <div className="offset-lg-3 col-lg-6" style={{marginTop: '100px', opacity: 0.8}}>
                    <form className="container" onSubmit={handlesubmit}>
                        <div className="card bg-dark">
                            <div className="card-header text-center text-bg-light">
                                Profil erstellen
                            </div>
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>E-Mail<span className="errmsg">*</span></label>
                                            <input
                                                value={email}
                                                onChange={(e) => setEmail(e.target.value)}
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>Passwort<span className="errmsg">*</span></label>
                                            <input
                                                value={password}
                                                onChange={(e) => setPassword(e.target.value)}
                                                type="password"
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>Benutzername<span className="errmsg">*</span></label>
                                            <input
                                                value={username}
                                                onChange={(e) => setUsername(e.target.value)}
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                    <div className="col-lg-6">
                                        <div className="form-group">
                                            <label style={{color:"white"}}>Handynummer</label>
                                            <input
                                                value={phone}
                                                onChange={(e) => setPhone(e.target.value)}
                                                className="form-control"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div className="card-footer text-center">
                                <button type="submit" className="btn btn-success" style={{width: "150px"}}>
                                    Registrieren
                                </button>
                                |
                                <Link to={'/login'} className="btn btn-primary" style={{width: "150px"}}>
                                    Login
                                </Link>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}


//export default CreateAccount-Ausdruck macht die CreateAccount-Komponente in anderen Dateien über ein import-Statement verfügbar.
export default CreateAccount;
