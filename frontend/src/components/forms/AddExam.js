import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Button from "react-bootstrap/Button";
import NewExamInfo from "./formParts/NewExamInfo";
import NewSubexamInfo from "./formParts/NewSubexamInfo";

class AddExam extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            showSubexamModal: false,
            userData: this.getInitialState(),
            majors: [],
            buildings: [],
            rooms: [],
        };
    }

    setShowConfirmationModal = (show) => {
    this.setState({
                      showConfirmationModal: show
                  });
}
setShowSubexamModal = (show) => {
    this.setState({
        showSubexamModal: show
    });
}

handleCloseConfirmationModal = () => this.setShowConfirmationModal(false);
handleOpenConfirmationModal = () => this.setShowConfirmationModal(true);
handleOpenSubexamModal = () => this.setShowSubexamModal(true);

handleSaveAndOpenSubexam = () => {
    let promise = new Promise(this.handleSave);
    promise.then(
        this.handleOpenSubexamModal
    ).catch(
        () => {console.log("err")}
)
}

setValidated = (validated) => {
    this.setState({
        validated
    });
}

getInitialState = () => {
    return {
        name: "",
        major: "WIEiT",
        startDate: "",
        endDate: "",
        recruitmentCycle: -1,
    }
}

handleInputChange = (event) => {
    let currentUserData = this.state.userData;
    this.setState({
        userData: {
            ...currentUserData,
            [event.target.id]: event.target.value
        }
    });
}

getFormData = (target, value) => {
    let currentUserData = this.state.userData;
    this.setState({
        userData: {
            ...currentUserData,
            [target]: value
        }
    })
}

handleSave = (resolve, reject) => {
    let form = this.formRef.current;
    if (!form.checkValidity()) {
        this.setValidated(true);
        reject();
    }
    else {
        this.props.handleHide();
        let userDataToSend = {
            name: this.state.userData.name,
            major: this.state.userData.major.fullName,
            startDate: this.state.userData.startDate,
            endDate: this.state.userData.endDate,
            recruitmentCycle: 1
        }
        console.log(userDataToSend);
        fetch("/new-exam", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userDataToSend)
        })
            .then((response) => response.json())
    .then(() => {
            this.setState({
                userData: this.getInitialState()
            });
        this.setValidated(false);
        resolve();
    })
    .catch((error) => console.log(error));
    }
}

hideAndClearState = () => {
    this.setState({
        userData: this.getInitialState()
    });
    this.setValidated(false);
    this.props.handleHide();
}

componentDidMount() {
    this.findMajors()
    this.findRooms()
}

findRooms(){
    fetch("/newest-rooms")
        .then((response)=>{
            return response.json();
        })
        .then((response) => {
            console.log(response);
            let roomsValues = response.map(value => value.number);
            let buildingsValues = response.map(value => value.localization);

            this.setState({
                rooms: roomsValues
            });
            this.setState({
                buildings: buildingsValues
            });
        });
}

findMajors() {
    fetch("/newest-majors")
        .then((response)=>{
            return response.json();
        })
        .then((response) => {
            console.log(response);
            let majorsValues = response.map(value => value.fullName);
            this.setState({
                majors: majorsValues
            });
        });
}

render() {
    return (
        <>
        <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
    backdrop={"static"} keyboard={false}>
            <Modal.Header closeButton className={"modal-form-bg-color"}>
                <Modal.Title className={"custom-margins custom-font text-light"}>Dodawanie nowego egzaminu</Modal.Title>
            </Modal.Header>
    <Modal.Body className={"custom-margins"}>
        <Form noValidate validated={this.state.validated} ref={this.formRef}>
        <NewExamInfo options={this.props.options} getFormData={this.getFormData} majors={this.state.majors} buildings={this.state.buildings} rooms={this.state.rooms}/>
    <h4 className={"mt-3 text-center"}>Uwagi</h4>
        <FormGroup controlId={"annotations"}>
    <Form.Control as={"textarea"} rows={"4"} onChange={this.handleInputChange}/>
    </FormGroup>
    </Form>
    </Modal.Body>
    <Modal.Footer>
    <Button variant={"primary"} className={"custom-margins"} onClick={this.handleSaveAndOpenSubexam}>
        Dodaj subegzamin
    </Button>

    </Modal.Footer>
    </Modal>
    <Modal show={this.state.showConfirmationModal} onHide={this.handleCloseConfirmationModal}>
        <Modal.Body>
        <h4 className={"text-center"}>Egzamin został dodany pomyślnie</h4>
    </Modal.Body>
    <Modal.Footer>
    <Button variant={"success"} onClick={this.handleCloseConfirmationModal}>OK</Button>
        </Modal.Footer>
        </Modal>
        </>
);
}
}

export default AddExam;
