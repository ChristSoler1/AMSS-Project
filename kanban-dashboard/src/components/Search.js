import {Link, useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

import Header from "./common/Header";
import {API_URL} from "../App";
import {toast} from "react-toastify";
import ModuleAddImage from "../resources/module-add.svg";
import TaskAddImage from "../resources/plus-square.svg";

/**
 * Search-Komponente, die die Hauptseite betreffend Funktionalität der Anwendung darstellt.
 * Ermöglicht das Erstellen, Anzeigen und Bearbeiten von Modulen und Aufgaben.
 */

const Search = () => {
    //Nimmt Benutzer-ID aus Session Speicher
    let userId = sessionStorage.getItem("userId")


    //Hook von React und gibt die Werte zurück
    const navigate = useNavigate()
    const [recordsModulesOfUser, setRecordsModulesOfUser] = useState([]);
    const [isModulePopupVisible, setModulePopupVisible] = useState(false);
    const [recordsPerformanceRecords, setPerformanceRecords] = useState([]);
    const [isModuleDetailsPopupVisible, setModuleDetailsPopupVisible] = useState(false);
    const [isTaskPopupVisible, setTaskPopupVisible] = useState(false);
    const [recordsStatus, setRecordsStatus] = useState([]);
    const [recordsPriority, setRecordsPriority] = useState([]);
    const [moduleData, setModuleData] = useState([]);
    const [moduleId, setModuleId] = useState('');
    const [isStatusDisabled, setIsStatusDisabled] = useState(false);
    const [isTaskDetailsPopupVisible, setTaskDetailsPopupVisible] = useState(false);

    const [formData, setFormData] = useState({
        title: '',
        credits: '',
        presetEffort: '',
        teachingResources: '',
        learningObjectives: '',
        performanceRecordId: '',
        endDate:'',
        startDate:'',
        userId: userId
    });

    const [taskFormData, setTaskFormData] = useState({
        title: '',
        description: '',
        presetEffort: '',
        actualEffort: '',
        statusId: '',
        priorityId: '',
        endDate: '',
        moduleId: moduleId
    });

    const openTaskPopup = () => {
        setTaskPopupVisible(true);
    };
    const closeTaskPopup = () => {
        setModuleId('')
        setIsStatusDisabled(false)
        setTaskPopupVisible(false);
        setTaskFormData({
            title: '',
            description: '',
            presetEffort: '',
            actualEffort: '',
            statusId: '',
            priorityId: '',
            endDate: '',
            moduleId: ''
        });
        load()
    };
    const openModulePopup = () => {
        setModulePopupVisible(true);
    };
    const closeModulePopup = () => {
        setModulePopupVisible(false);
        load()
    };
    const openModuleDetailsPopup = () => {
        setModuleDetailsPopupVisible(true);
    };
    const closeModuleDetailsPopup = () => {
        setModuleData(null)
        setModuleDetailsPopupVisible(false);
    };

    const openTaskDetailsPopup = () => {
        setTaskDetailsPopupVisible(true);
    };
    const closeTaskDetailsPopup = () => {
        setTaskFormData({
            title: '',
            description: '',
            presetEffort: '',
            actualEffort: '',
            statusId: '',
            priorityId: '',
            endDate: '',
            moduleId: ''
        });
        setIsStatusDisabled(false)
        setTaskDetailsPopupVisible(false);
        load()
    };

    function openTaskDetails(task, status) {
        if (status !== 0) {
            setIsStatusDisabled(true)
        }
        taskFormData.id = task.entity.id
        taskFormData.title = task.entity.title
        taskFormData.description = task.entity.description
        taskFormData.actualEffort = task.entity.actualEffort
        taskFormData.moduleId = task.entity.moduleId
        taskFormData.statusId = task.entity.statusId
        taskFormData.presetEffort = task.entity.presetEffort
        taskFormData.priorityId = task.entity.priorityId
        taskFormData.endDate = task.entity.endDate
        openTaskDetailsPopup()
    }

    function openModuleDetails(record, name) {
        record.performanceRecordsName = name
        setModuleData(record)
        openModuleDetailsPopup()
    }

    function openTask(moduleId, status) {
        setModuleId(moduleId)
        if (status === 1) {
            taskFormData.statusId = 1
        } else if (status === 2) {
            taskFormData.statusId = 2
        } else if (status === 4) {
            setIsStatusDisabled(true)
        }
        openTaskPopup()
    }


    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const handleTaskFormDataChange = (e) => {
        const {name, value} = e.target;
        setTaskFormData({...taskFormData, [name]: value});
    };
    const handleModuleFormDataChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const handlesubmit = (e) => {
        e.preventDefault();
        if (!formData.title || !formData.credits || !formData.presetEffort || !formData.performanceRecordId) {
            console.log('Please fill in all required fields (marked with *)');
            return;
        }
        fetch(API_URL + "/v1/kb/module/create", {
            method: "POST",
            headers: {'content-type': 'application/json'},
            body: JSON.stringify(formData)
        }).then(res => {
            return res.json();
        }).then((resp) => {
            if (resp.responseCode !== "200 OK") {
                console.log(resp.responseMsg);
            } else {
                console.log('MODULE CREATED')
                setFormData({
                    title: '',
                    credits: '',
                    presetEffort: '',
                    teachingResources: '',
                    learningObjectives: '',
                    performanceRecordId: '',
                    endDate:'',
                    startDate: '',
                    userId: userId
                });
                closeModulePopup()
                window.location.reload();
            }
        }).catch((err) => {
            console.log('Failed :' + err.message);
        });

    }

    const handleTaskSubmit = (e) => {
        e.preventDefault();
        taskFormData.moduleId = moduleId

        if (isStatusDisabled) {
            taskFormData.statusId = 4
            taskFormData.presetEffort = 0
            taskFormData.actualEffort = 0
            taskFormData.priorityId = 1
            taskFormData.endDate = '2023/11/25'
        }

        if (!taskFormData.title || !taskFormData.statusId || !taskFormData.presetEffort || !taskFormData.actualEffort || !taskFormData.endDate) {
            console.log('Please fill in all required fields (marked with *)');
            return;
        }
        fetch(API_URL + "/v1/kb/tasks/create", {
            method: "POST",
            headers: {'content-type': 'application/json'},
            body: JSON.stringify(taskFormData)
        }).then(res => {
            return res.json();
        }).then((resp) => {
            if (resp.responseCode !== "200 OK") {
                console.log(resp.responseMsg);
            } else {
                toast.success('TASK CREATED')
                setTaskFormData({
                    title: '',
                    description: '',
                    presetEffort: '',
                    actualEffort: '',
                    statusId: '',
                    priorityId: '',
                    endDate: '',
                    moduleId: ''
                });
                closeTaskPopup()
            }
        }).catch((err) => {
            console.log('Failed :' + err.message);
        });

    }

    const handleUpdateTaskSubmit = (e) => {
        e.preventDefault();
        if (isStatusDisabled) {
            taskFormData.statusId = 4
            taskFormData.presetEffort = 1
            taskFormData.priorityId = 3
            taskFormData.endDate = '2023/11/25'
        }

        if (!taskFormData.title || !taskFormData.statusId || !taskFormData.presetEffort || !taskFormData.endDate) {
            console.log('Please fill in all required fields (marked with *)');
            return;
        }
        fetch(API_URL + "/v1/kb/tasks/update", {
            method: "PUT",
            headers: {'content-type': 'application/json'},
            body: JSON.stringify(taskFormData)
        }).then(res => {
            return res.json();
        }).then((resp) => {
            if (resp.responseCode !== "200 OK") {
                console.log(resp.responseMsg);
            } else {
                toast.success('TASK UPDATED')
                setTaskFormData({
                    title: '',
                    description: '',
                    presetEffort: '',
                    actualEffort: '',
                    statusId: '',
                    priorityId: '',
                    endDate: '',
                    moduleId: ''
                });
                closeTaskDetailsPopup()
            }
        }).catch((err) => {
            console.log('Failed :' + err.message);
        });

    }


    useEffect(() => {

        if (userId === "" || userId === null) {
            navigate("/login")
        }

        load()

    }, []);

    function load() {
        fetch(API_URL + "/v1/kb/module/getallbyuserid/" + userId, {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setRecordsModulesOfUser(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });

        fetch(API_URL + "/v1/kb/performancerecords/get", {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setPerformanceRecords(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });

        fetch(API_URL + "/v1/kb/taskstatuses/get", {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setRecordsStatus(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });

        fetch(API_URL + "/v1/kb/priorities/get", {
                method: "GET"
            }
        ).then((res) => {
            return res.json();
        }).then((resp) => {
            setRecordsPriority(resp.content)
        }).catch((err) => {
            console.log('Failed to get data :' + err.message);
        });
    }

    return (
        <div>
            <Header/>
            {isModulePopupVisible && (
                <div className="popup">
                    <div className="popup-content bg-dark">
                        <form className="container" onSubmit={handlesubmit}>
                            <div className="card bg-dark">
                                <div className="card-header text-center text-bg-light">
                                    Modul erstellen
                                </div>
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Titel<span
                                                    className="errmsg">*</span></label>
                                                <input
                                                    value={formData.title}
                                                    onChange={handleChange}
                                                    className="form-control"
                                                    type="text"
                                                    id="title"
                                                    name="title"
                                                />
                                            </div>

                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Leistungsnachweis<span
                                                    className="errmsg">*</span></label>
                                                <select
                                                    className="form-select"
                                                    id="performanceRecordId"
                                                    name="performanceRecordId"
                                                    value={formData.performanceRecordId}
                                                    onChange={handleChange}
                                                >
                                                    <option value="">Leistungsnachweis</option>
                                                    {recordsPerformanceRecords.map((type) => (
                                                        <option className="form-control" key={type.id}
                                                                value={type.id}>
                                                            {type.name}
                                                        </option>
                                                    ))}
                                                </select>
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Credits<span className="errmsg">*</span></label>
                                                <input
                                                    value={formData.credits}
                                                    onChange={handleChange}
                                                    type="number"
                                                    className="form-control"
                                                    id="credits"
                                                    name="credits"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Vorgegebener Aufwand<span
                                                    className="errmsg">*</span></label>
                                                <input
                                                    value={formData.presetEffort}
                                                    onChange={handleChange}
                                                    type="number"
                                                    className="form-control"
                                                    id="presetEffort"
                                                    name="presetEffort"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Lehrmittel</label>
                                                <textarea
                                                    value={formData.teachingResources}
                                                    onChange={handleChange}
                                                    className="form-control"
                                                    id="teachingResources"
                                                    name="teachingResources"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Kompetenzen</label>
                                                <textarea
                                                    value={formData.learningObjectives}
                                                    onChange={handleChange}
                                                    className="form-control"
                                                    id="learningObjectives"
                                                    name="learningObjectives"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Startdatum</label>
                                                <input
                                                    type="date"
                                                    value={formData.startDate}
                                                    onChange={handleModuleFormDataChange}
                                                    className="form-control"
                                                    id="startDate"
                                                    name="startDate"
                                                />
                                            </div>
                                        </div>
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Enddatum</label>
                                                <input
                                                    type="date"
                                                    value={formData.endDate}
                                                    onChange={handleModuleFormDataChange}
                                                    className="form-control"
                                                    id="endDate"
                                                    name="endDate"
                                                />
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div className="card-footer text-center">
                                    <button type="submit" className="btn btn-success" style={{width: "100px"}}>
                                        Fertig
                                    </button>
                                    |
                                    <button to={'/login'} className="btn btn-primary" style={{width: "150px"}}
                                            onClick={closeModulePopup}>Schliessen</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            )}

            {(isTaskPopupVisible || isTaskDetailsPopupVisible) && (
                <div className="popup">
                    <div className="popup-content bg-dark">
                        <form className="container"
                              onSubmit={isTaskPopupVisible ? handleTaskSubmit : handleUpdateTaskSubmit}>
                            <div className="card bg-dark">
                                <div className="card-header text-center" style={{color: "white"}}>
                                    Task erstellen
                                </div>
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-lg-6">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Titel<span
                                                    className="errmsg">*</span></label>
                                                <input
                                                    value={taskFormData.title}
                                                    onChange={handleTaskFormDataChange}
                                                    className="form-control"
                                                    type="text"
                                                    id="title"
                                                    name="title"
                                                />
                                            </div>

                                        </div>
                                        {!isStatusDisabled && (
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label style={{color: "white"}}>Status<span
                                                        className="errmsg">*</span></label>
                                                    <select
                                                        className="form-select"
                                                        id="statusId"
                                                        name="statusId"
                                                        value={taskFormData.statusId}
                                                        onChange={handleTaskFormDataChange}
                                                    >
                                                        <option value="">Beschreibung</option>
                                                        {recordsStatus.map((type) => (
                                                            <option className="form-control" key={type.id}
                                                                    value={type.id}>
                                                                {type.name}
                                                            </option>
                                                        ))}
                                                    </select>
                                                </div>
                                            </div>
                                        )}
                                        <div className="mb-3">
                                            <div className="form-group">
                                                <label style={{color: "white"}}>Description</label>
                                                <textarea
                                                    value={taskFormData.description}
                                                    onChange={handleTaskFormDataChange}
                                                    className="form-control"
                                                    id="description"
                                                    name="description"
                                                />
                                            </div>
                                        </div>
                                        {!isStatusDisabled && (
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label style={{color: "white"}}>Vorgegebener Aufwand<span
                                                        className="errmsg">*</span></label>
                                                    <input
                                                        value={taskFormData.presetEffort}
                                                        onChange={handleTaskFormDataChange}
                                                        type="number"
                                                        className="form-control"
                                                        id="presetEffort"
                                                        name="presetEffort"
                                                    />
                                                </div>
                                            </div>)}
                                        {!isStatusDisabled && (
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label style={{color: "white"}}>Tatsächlicher Aufwand</label>
                                                    <input
                                                        value={taskFormData.actualEffort}
                                                        onChange={handleTaskFormDataChange}
                                                        type="number"
                                                        className="form-control"
                                                        id="actualEffort"
                                                        name="actualEffort"
                                                    />
                                                </div>
                                            </div>)}
                                        {!isStatusDisabled && (
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label style={{color: "white"}}>Enddatum<span
                                                        className="errmsg">*</span></label>
                                                    <input
                                                        type="date"
                                                        value={taskFormData.endDate}
                                                        onChange={handleTaskFormDataChange}
                                                        className="form-control"
                                                        id="endDate"
                                                        name="endDate"
                                                    />
                                                </div>
                                            </div>)}
                                        {!isStatusDisabled && (
                                            <div className="col-lg-6">
                                                <div className="form-group">
                                                    <label style={{color: "white"}}>Priorität<span
                                                        className="errmsg">*</span></label>
                                                    <select
                                                        className="form-select"
                                                        id="priorityId"
                                                        name="priorityId"
                                                        value={taskFormData.priorityId}
                                                        onChange={handleTaskFormDataChange}
                                                    >
                                                        <option value="">Wähle Priorität</option>
                                                        {recordsPriority.map((type) => (
                                                            <option className="form-control" key={type.id}
                                                                    value={type.id}>
                                                                {type.level}
                                                            </option>
                                                        ))}
                                                    </select>
                                                </div>
                                            </div>
                                        )}
                                    </div>
                                </div>
                                <div className="card-footer text-center">
                                    <button type="submit"
                                            className={`btn ${isTaskPopupVisible ? 'btn-success' : 'btn-warning'}`}
                                            style={{width: "150px"}}>
                                        {isTaskPopupVisible ? 'Fertig' : 'aktualisieren'}
                                    </button>
                                    |
                                    {isTaskPopupVisible && (
                                        <button className="btn btn-primary" style={{width: "150px"}}
                                                onClick={closeTaskPopup}>Schliessen</button>
                                    )}
                                    {isTaskDetailsPopupVisible && (
                                        <button className="btn btn-primary" style={{width: "150px"}}
                                                onClick={closeTaskDetailsPopup}>Schliessen</button>
                                    )}
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            )}

            {isModuleDetailsPopupVisible && (
                <div className="popup">
                    <div className="popup-content bg-dark">
                        <form className="container">
                            <div className="card bg-dark">
                                <div className="card-header text-center" style={{color: "white"}}>
                                    Modul Details
                                </div>
                                <div className="card-body text-center">
                                    <div style={{color: "white"}}>
                                        Modul ID : {moduleData.id}<br/>
                                        Titel : {moduleData.title}<br/>
                                        Credits : {moduleData.credits}<br/>
                                        Kompetenzen : {moduleData.learningObjectives}<br/>
                                        Leistungsnachweis : {moduleData.performanceRecordsName}<br/>
                                        Vorgegebener Aufwand : {moduleData.presetEffort}H<br/>
                                        Lehrmittel : {moduleData.teachingResources}<br/>
                                        Startdatum : {moduleData.startDate ? new Date(moduleData.startDate).toLocaleDateString('de-DE', {
                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                    }) : ''}<br/>
                                        Enddatum : {moduleData.endDate  ? new Date(moduleData.endDate).toLocaleDateString('de-DE', {
                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                    }) : ''}<br/>
                                    </div>
                                    <br/>
                                </div>
                                <div className="card-footer text-center">
                                    <button className="btn btn-primary" style={{width: "150px"}}
                                            onClick={closeModuleDetailsPopup}>Schliessen
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            )}


            <div style={{marginTop: "80px"}}>
                <div className="row col-lg-12 mt-3" style={{marginLeft: "5%"}}>
                    <div className="col-2 text-center mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <input type="text" value="Module" disabled={true} style={{color: "white"}}
                               className="form-control bg-dark"/>
                    </div>
                    <div className="col-2 text-center mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <input type="text" value="Offene Tasks" disabled={true} style={{color: "white"}}
                               className="form-control bg-dark"/>
                    </div>
                    <div className="col-2 text-center mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <input type="text" value="In Bearbeitung" disabled={true} style={{color: "white"}}
                               className="form-control bg-dark"/>
                    </div>
                    <div className="col-2 text-center mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <input type="text" value="Beendet" disabled={true} style={{color: "white"}}
                               className="form-control bg-dark"/>
                    </div>
                    <div className="col-2 text-center mt-3"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <input type="text" value="gelernt" disabled={true} style={{color: "white"}}
                               className="form-control bg-dark"/>
                    </div>
                </div>
                <div className="row col-3 mt-1" style={{marginLeft: "5%"}}>
                    <div className="col-4 text-center"
                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                        <button onClick={openModulePopup} className=" form-control image-button bg-dark">
                            <img src={ModuleAddImage} alt="add new module"/>
                        </button>
                    </div>
                </div>
                {recordsModulesOfUser.map((record, index) => (
                    <div key={index} className="row col-lg-12 mt-1" style={{marginLeft: "5%"}}>
                        <div className="col-2 mt-3"
                             style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                            <div className="card h-100 w-100 bg-dark"
                                 onDoubleClick={() => openModuleDetails(record.entity, record.performanceRecordsName)}>
                                <div className="card-body " style={{color: "white"}}>
                                    <h5 className="card-title">{record.entity.title}</h5><br/>
                                    <p className="card-text">Soll: {record.entity.presetEffort}H</p>
                                    <p className="card-text">Verbleibend: {record.lstEffort}H</p>
                                    <p className="card-text">Startdatum:{record.entity.startDate ? new Date(record.entity.startDate).toLocaleDateString('de-DE', {
                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                    }) : ''}</p>
                                    <p className="card-text">Enddatum: {record.entity.endDate ? new Date(record.entity.endDate).toLocaleDateString('de-DE', {
                                        year: '2-digit', month: '2-digit', day: '2-digit',
                                    }) : ''}</p>
                                </div>
                            </div>
                        </div>
                        <div className="col-2 mt-3"
                             style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                            <div className="card h-100 w-100 bg-dark">
                                <div className="card-body">
                                    {record.openTasks && record.openTasks.map((task, index) => (
                                        <div className="card text-white bg-info mb-3"
                                             onDoubleClick={() => openTaskDetails(task, 0)}>
                                            <div className="card-body" style={{color: "black"}}>
                                                <p className="card-text">{task.entity.title} <br/>
                                                    <span style={{fontSize: "12px"}}>
                                                 {task.entity.description && task.entity.description.length > 150 ? task.entity.description.substring(0, 75) + '...': task.entity.description
                                                 }<br/>
                                                        {task.entity.presetEffort && ` vorgegebener Aufwand: ${task.entity.presetEffort}H`}<br/>
                                                        {task.entity.actualEffort && ` tatsächlicher Aufwand: ${task.entity.actualEffort}H`}<br/>
                                                        {task.entity.endDate && ` Enddatum: ${task.entity.endDate ? new Date(task.entity.endDate).toLocaleDateString('de-DE', {
                                                            year: '2-digit', month: '2-digit', day: '2-digit',
                                                        }) : ''}`}<br/>
                                                    Priorität: <b><span
                                                        className={`${task.entity.priorityId === 1 ? 'text-danger' : ''}
                                                        ${task.entity.priorityId === 2 ? 'text-dark' : ''}
                                                        ${task.entity.priorityId === 3 ? 'text-light' : ''}`}>
                                                       {task.level && task.level}
                                                        {task.entity.priorityId === 3 ? '▼' : task.entity.priorityId === 2 ? '▲' : '▲▲'}
                                                   </span></b>
                                            </span>
                                                </p>
                                            </div>
                                        </div>
                                    ))}
                                    <div className="col-4 text-center"
                                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                                        <button onClick={() => openTask(record.entity.id, 1)}
                                                className=" form-control image-button bg-dark">
                                            <img src={TaskAddImage} alt="add new task"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-2 mt-3"
                             style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                            <div className="card h-100 w-100 bg-dark">
                                <div className="card-body">
                                    {record.inProgressTasks && record.inProgressTasks.map((task, index) => (
                                        <div className="card text-white bg-warning mb-3"
                                             onDoubleClick={() => openTaskDetails(task, 0)}>
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
                                                    Priorität: <b><span
                                                        className={`${task.entity.priorityId === 1 ? 'text-danger' : ''}
                                                        ${task.entity.priorityId === 2 ? 'text-dark' : ''}
                                                        ${task.entity.priorityId === 3 ? 'text-light' : ''}`}>
                                                       {task.level && task.level}
                                                        {task.entity.priorityId === 3 ? '▼' : task.entity.priorityId === 2 ? '▲' : '▲▲'}
                                                   </span></b>
                                            </span>
                                                </p>
                                            </div>
                                        </div>
                                    ))}
                                    <div className="col-4 text-center"
                                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                                        <button onClick={() => openTask(record.entity.id, 2)}
                                                className=" form-control image-button bg-dark">
                                            <img src={TaskAddImage} alt="add new task"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-2 mt-3"
                             style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                            <div className="card h-100 w-100 bg-dark">
                                <div className="card-body">
                                    {record.finishedTasks && record.finishedTasks.map((task, index) => (
                                        <div className="card text-white bg-success mb-3"
                                             onDoubleClick={() => openTaskDetails(task, 0)}>
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
                        <div className="col-2 mt-3"
                             style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                            <div className="card h-100 w-100 bg-dark">
                                <div className="card-body">
                                    {record.learned && record.learned.map((task, index) => (
                                        <div className="card text-white bg-secondary mb-3"
                                             onDoubleClick={() => openTaskDetails(task, 1)}>
                                            <div className="card-body" style={{color: "black"}}>
                                                <p className="card-text">{task.entity.title} <br/>
                                                    <span style={{fontSize: "12px"}}>
                                                 {task.entity.description && task.entity.description}
                                            </span>
                                                </p>
                                            </div>
                                        </div>
                                    ))}
                                    <div className="col-4 text-center"
                                         style={{display: 'flex', alignItems: 'center', marginLeft: "10px"}}>
                                        <button onClick={() => openTask(record.entity.id, 4)}
                                                className=" form-control image-button bg-dark">
                                            <img src={TaskAddImage} alt="add new task"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                ))}

            </div>
        </div>


    )
}

export default Search;
