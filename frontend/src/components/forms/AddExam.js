import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Button from "react-bootstrap/Button";
import NewExamInfo from "./formParts/NewExamInfo";

class AddExam extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            userData: this.getInitialState(),
            majors: ["Informatyka", "Informatyka Społeczna", "Humanistyka", "Telekomunikacja", "Elektronika"],
            buildings: ["D17", "D10", "A0", "B1", "D11", "C2"],
            rooms: ["1.38", "2.41", "3.22", "3.23", "3.27A", "3.27B", "3.27C"]
        };
    }

    setShowConfirmationModal = (show) => {
    this.setState({
                      showConfirmationModal: show
                  });
}

handleCloseConfirmationModal = () => this.setShowConfirmationModal(false);
handleOpenConfirmationModal = () => this.setShowConfirmationModal(true);

handleSaveAndOpenConfirm = () => {
    let promise = new Promise(this.handleSave);
    promise.then(
        this.handleOpenConfirmationModal
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
        major: "Informatyka",
        modeOfStudy: "stacjonarne",
        startDate: "",
        endDate: "",
        building: "D17",
        room: "1.38"
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
            major: this.state.userData.major,
            modeOfStudy: this.set.userData.modeOfStudy,
            startDate: this.state.userData.startDate,
            endDate: this.state.userData.endDate,
            building: this.state.userData.building,
            room: this.state.userData.room
        }
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

render() {
    return (
        <>
        <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
    backdrop={"static"} keyboard={false}>
        <Modal.Header closeButton>
    <Modal.Title className={"custom-margins"}>Dodawanie nowego egzaminu</Modal.Title>
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
    <Button variant={"primary"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
        Dodaj egzamin
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