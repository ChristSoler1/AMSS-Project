import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

import Header from "./common/Header";
import {API_URL} from "../App";

/**
 * Home-Komponente, die die Hauptseite der Anwendung darstellt.
 * Zeigt bevorstehende Aufgaben, Aufgaben mit hoher Priorität und einen Kalender an.
 */

const Home = () => {

    const navigate = useNavigate()
    const [records, setRecords] = useState([]);
    const [recordsHighPriorityTasks, setRecordsHighPriorityTasks] = useState([]);
    const [isPopupVisible, setPopupVisible] = useState(false);
    const [calenderUrl, setCalenderUrl] = useState('');
    let userId = sessionStorage.getItem("userId")
    const [formData, setFormData] = useState({
        userId: userId,
        calenderUrl: '',
    });


    const updateDivSize = () => {
        const container = document.getElementById('calendarContainer');
        if (container) {
            const windowHeight = window.innerHeight;
            container.style.height = windowHeight - 100 + 'px'; // Adjust the value according to your needs
        }
    };

    useEffect(() => {
        window.addEventListener('resize', updateDivSize);
        updateDivSize();
        return () => {
            window.removeEventListener('resize', updateDivSize);
        };
    }, []);

    useEffect(() => {
        setCalenderUrl(sessionStorage.getItem('calenderUrl'))
        console.log(calenderUrl)
        if (userId === "" || userId === null) {
            navigate("/login")
        }
        fetch(API_URL + "/v1/kb/tasks/getopentasks/" + userId, {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setRecords(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });

        fetch(API_URL + "/v1/kb/tasks/gethighprioritytasks/" + userId, {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setRecordsHighPriorityTasks(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });

    }, []);

    const openPopup = () => {
        setPopupVisible(true);
    };
    const closePopup = () => {
        setPopupVisible(false);
    };

    const UpdateCalenderUrl = (e) => {
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
                console.log('calender URL Updated successfully.')
                sessionStorage.setItem('calenderUrl', formData.calenderUrl);
                closePopup()
                window.location.reload()
            }
        }).catch((err) => {
            console.log('Failed :' + err.message);
        });

        console.log(calenderUrl)
    }

    const handleChange = (e) => {
        const {name, value, type} = e.target;
        setFormData({...formData, [name]: value});
    };

    const getCalendarUrl = () => {
        console.log(calenderUrl)
        return calenderUrl.trim() !== '' ? calenderUrl : 'N/A';
    };

    const resizeIframe = () => {
        const iframe = document.getElementById('calendarIframe');
        if (iframe) {
            iframe.style.height = window.innerHeight + 'px';
        }
    };

    return (
        <div>

            {isPopupVisible && (
                <div className="popup">
                    <div className="popup-content bg-dark">
                        <form onSubmit={UpdateCalenderUrl} className="container">
                            <div className="card bg-dark">
                                <div className="card-body">
                                    <div className="row">
                                        <div className="mb-3">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Kalender URL</label>
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
                                            ADD
                                        </button>
                                        |
                                        <button className="btn btn-primary" style={{width: "150px"}}
                                                onClick={closePopup}>Schliessen
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            )}
            <Header/>
            <div style={{marginTop: "100px"}}>

                <div className="row col-lg-12 mt-3" style={{marginLeft: "5%"}}>
                    <div className="col-lg-5 mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <div className="card h-100 w-100 bg-dark">
                            <div className="card-header text-light">
                                Bevorstehende Tasks
                            </div>
                            <div className="card-body">
                                {records.map((task, index) => (
                                    <div className="card text-white bg-info mb-3">
                                        <div className="card-body" style={{color: "black"}}>
                                            <p className="card-text">{task.entity.title} <br/>
                                                <span style={{fontSize: "12px"}}>
                                                 {task.entity.description && task.entity.description}<br/>
                                                    {task.entity.presetEffort && ` vorgegebener Aufwand: ${task.entity.presetEffort}H`}<br/>
                                                    tatsächlicher Aufwand: {task.entity.actualEffort ? (
                                                    <span>{task.entity.actualEffort}H</span>
                                                ) : (
                                                    <span>N/A</span>
                                                )}<br/>
                                                    {task.entity.endDate && ` Enddatum: ${task.entity.endDate ? new Date(task.entity.endDate).toLocaleDateString('de-DE', {
                                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                                    }) : ''}`}<br/>
                                            </span>
                                            </p>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                    <div className="col-lg-5 mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <div className="card h-100 w-100 bg-dark">
                            <div className="card-header text-light">
                                Tasks mit hoher Priorität
                            </div>
                            <div className="card-body">
                                {recordsHighPriorityTasks.map((task, index) => (
                                    <div className="card text-white bg-danger mb-3">
                                        <div className="card-body">
                                            <p className="card-text">{task.entity.title} <br/>
                                                <span style={{fontSize: "12px"}}>
                                                 {task.entity.description && task.entity.description}<br/>
                                                    {task.entity.presetEffort && ` vorgegebener Aufwand: ${task.entity.presetEffort}H`}<br/>
                                                    tatsächlicher Aufwand: {task.entity.actualEffort ? (
                                                    <span>{task.entity.actualEffort}H</span>
                                                ) : (
                                                    <span>N/A</span>
                                                )}<br/>
                                                    {task.entity.endDate && ` Enddatum: ${task.entity.endDate ? new Date(task.entity.endDate).toLocaleDateString('de-DE', {
                                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                                    }) : ''}`}<br/>
                                            </span>
                                            </p>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
                <div className="offset-lg-1 col-lg-9" style={{ marginTop: '50px', marginLeft: '10%' }}>
                    <div id="calendarContainer">
                        <div className="card h-100 w-100 bg-dark" onDoubleClick={openPopup}>
                            <div className="card-header text-light">Kalender</div>
                            <div className="card-body">
                                {calenderUrl && (
                                    <iframe
                                        id="calendarIframe"
                                        title="Outlook Calendar"
                                        src={getCalendarUrl()}
                                        width="100%"
                                        height="100%"
                                        frameBorder="0"
                                        scrolling="yes"
                                    ></iframe>
                                )}
                                {!calenderUrl && (
                                    <button onClick={openPopup} className="bg-dark form-control text-light">
                                        Füge deinen Kalender hinzu
                                    </button>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Home;
