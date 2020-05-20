
import React from "react";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import NewExamInfo from "./formParts/NewExamInfo";
import NewSubExamInfo from "./formParts/NewSubexamInfo";
import Col from "react-bootstrap/Col";
import DataTable from "../dashboard/DataTable";
import Row from "react-bootstrap/Row";

class EditExam extends React.Component {

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
        if (this.props.initialInputValues) {
            return this.props.initialInputValues;
        }
        else {
            return {
                name: "",
                major: "",
                startDate: "",
                endDate: "",
                recruitmentCycleId: -1,
            }
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
                recruitmentCycleId: this.state.userData.recruitmentCycleId
            }
            console.log(userDataToSend);
            fetch(`/edit-exam/${this.props.initialInputValues.id}`, {
                method: 'PUT',
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
                        examId: this.state.userData.id
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
                        ...currentUserData
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
                        <Modal.Title className={"custom-margins custom-font text-light"}>Edytowanie egzaminu
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body className={"custom-margins"}>
                        <Form noValidate validated={this.state.validated} ref={this.formRef}>
                            <NewExamInfo options={this.props.options} getFormData={this.getFormData}
                                         majors={this.state.majors}
                                         rooms={this.state.rooms} inputValuesFromState={this.state.userData}/>
                            {/*<h4 className={"mt-3 text-center"}>Uwagi</h4>*/}
                            {/*<FormGroup controlId={"annotations"}>*/}
                            {/*    <Form.Control as={"textarea"} rows={"4"} onChange={this.handleInputChange}/>*/}
                            {/*</FormGroup>*/}
                        </Form>
                        <Row>
                            <Col>
                                <DataTable tableHeader={["Subexam id", "Sala", "Data", "Godzina"]}
                                           name={"Subexams"}
                                           mapping={`/newest-subexams/${this.state.userData.id}`}
                                           tableValues={["id", "room", "date", "time"]}/>
                            </Col>
                        </Row>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"primary"} className={"custom-margins"}
                                onClick={this.handleSaveAndOpenSubexam}>
                            Dodaj termin
                        </Button>
                        <Button variant={"secondary"} className={"custom-margins"}
                                onClick={this.handleSave}>
                            Zapisz zmiany
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

export default EditExam;
