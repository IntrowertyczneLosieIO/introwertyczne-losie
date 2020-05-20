import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Button from "react-bootstrap/Button";
import NewExamInfo from "./formParts/NewExamInfo";
import NewSubExamInfo from "./formParts/NewSubexamInfo";

class AddExam extends React.Component {
    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showSubexamModal: false,
            userData: this.getInitialState(),
            majors: [],
            rooms: [],
            examId: 0
        };
    }


    setShowSubexamModal = (show) => {
        this.setState({
            showSubexamModal: show
        });
    }

    handleOpenSubexamModal = () => this.setShowSubexamModal(true);
    handleCloseSubexamModal = () => this.setShowSubexamModal(false);

    handleSaveAndOpenSubexam = () => {
        let promise = new Promise(this.handleSave);
        promise.then(
            this.handleOpenSubexamModal
        ).catch(
            () => {
                console.log("err")
            }
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
            major: "",
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
        } else {
            this.props.handleHide();
            let userDataToSend = {
                name: this.state.userData.name,
                major: this.state.userData.major,
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
                .then((response) => {
                    this.setState({
                        userData: this.getInitialState()
                    });
                    this.setState({
                        examId: response
                    })
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

    findRooms() {
        fetch("/newest-rooms")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                this.setState({
                    rooms: response
                });
            });
    }

    findMajors() {
        fetch("/newest-majors")
            .then((response) => {
                return response.json();
            })
            .then((response) => {
                this.setState({
                    majors: response
                });
                let currentUserData = this.state.userData;
                this.setState({
                    userData: {
                        ...currentUserData,
                        major: this.state.majors[0].fullName
                    }
                })
            });
    }

    render() {
        return (
            <>
                <Modal show={this.props.show} dialogClassName={"custom-width-modal"} onHide={this.hideAndClearState}
                       backdrop={"static"} keyboard={false}>
                    <Modal.Header closeButton className={"modal-form-bg-color"}>
                        <Modal.Title className={"custom-margins custom-font text-light"}>Dodawanie nowego
                            egzaminu</Modal.Title>
                    </Modal.Header>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewExamInfo options={this.props.options} getFormData={this.getFormData}
                                         majors={this.state.majors}
                                         rooms={this.state.rooms}/>
                            <h4 className={"mt-3 text-center"}>Uwagi</h4>
                            <FormGroup controlId={"annotations"}>
                                <Form.Control as={"textarea"} rows={"4"} onChange={this.handleInputChange}/>
                            </FormGroup>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"primary"} className={"custom-margins"}
                                onClick={this.handleSaveAndOpenSubexam}>
                            Dodaj subegzamin
                        </Button>
                    </Modal.Footer>
                </Modal>

                <Modal show={this.state.showSubexamModal} onHide={this.handleCloseSubexamModal}>
                    <Modal.Body>
                        <NewSubExamInfo options={this.props.options} getFormData={this.getFormData}
                                        rooms={this.state.rooms}
                                        handleShow={this.handleOpenSubexamModal}
                                        handleHide={this.handleCloseSubexamModal}
                                        show={this.state.showAddNewSubexam}
                                        examId={this.state.examId}/>
                    </Modal.Body>
                </Modal>
            </>
        );
    }
}

export default AddExam;
