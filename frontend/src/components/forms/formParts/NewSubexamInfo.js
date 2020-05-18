import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";

class NewSubExamInfo extends React.Component {

    constructor(props) {
        super(props);
        this.formRef = React.createRef();
        this.state = {
            validated: false,
            showConfirmationModal: false,
            subexamList: [],
            subExam: {
                room: this.props.rooms[0].id + " " + this.props.rooms[0].number + " " + this.props.rooms[0].localization,
                date: "",
                time: ""
            }
        }

    }

    handleSaveAndOpenConfirm = () => {
        let promise = new Promise(this.handleSaveSubexam);
        promise.then(
            this.handleOpenConfirmationModal
        ).catch(
            (error) => {
                console.log(error)
            }
        )
    }

    handleCloseConfirmationModal = () => this.setShowConfirmationModal(false);
    handleOpenConfirmationModal = () => this.setShowConfirmationModal(true);

    setShowConfirmationModal = (show) => {
        this.setState({
            showConfirmationModal: show
        });
    }

    setValidated = (validated) => {
        this.setState({
            validated
        });
    }

    handleControlChange = (event) => {

        let subExam = this.state.subExam;
        this.setState({
            subExam: {
                ...subExam,
                [event.target.id]: event.target.value
            }
        });
    }

    addSubexam = () => {
        let form = this.formRef.current;
        if (!form.checkValidity()) {
            this.setValidated(true);
        } else {
            let currentSubexams = this.state.subexamList;
            this.setState({
                subexamList: [...currentSubexams, this.state.subExam]
            });
            this.setValidated(false);
            this.setState({
                subExam: {
                    room: "",
                    date: "",
                    time: ""
                }
            });
        }
    }

    handleSaveSubexam = (resolve, reject) => {
        this.props.handleHide();
        for (let subexam of this.state.subexamList) {
            let userDataToSend = {
                roomId: +subexam.room.split(" ")[0],
                date: subexam.date,
                time: subexam.time,
                examId: this.props.examId,
            }
            console.log(userDataToSend);
            fetch("/new-subexam", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userDataToSend)
            })
                .then((response) => response.json())
                .then(() => {
                    resolve();
                })
                .catch((error) => console.log(error));
        }
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o subegzaminie</h5>
                <Form noValidate validated={this.state.validated} ref={this.formRef}>
                    <Form.Row>
                        <FormGroup as={Col} controlId={"date"}>
                            <Form.Label>Dzień egzaminu</Form.Label>
                            <Form.Control type={"date"} onChange={this.handleControlChange} required
                                          value={this.state.subExam.date}/>
                        </FormGroup>
                        <FormGroup as={Col} controlId={"time"}>
                            <Form.Label>Godzina rozpoczęcia</Form.Label>
                            <Form.Control type={"time"} onChange={this.handleControlChange} required
                                          value={this.state.subExam.time}/>
                        </FormGroup>
                    </Form.Row>
                    {/*<Form.Row>*/}
                    {/*    <FormGroup as={Col} controlId={"endTime"}>*/}
                    {/*        <Form.Label>Godzina zakończenia</Form.Label>*/}
                    {/*        <Form.Control type={"time"} onChange={this.handleControlChange} required/>*/}
                    {/*    </FormGroup>*/}
                    {/*</Form.Row>*/}
                    <Form.Row>
                        <FormGroup as={Col} controlId={"room"}>
                            <Form.Label>Sala</Form.Label>
                            <Form.Control as={"select"} onChange={this.handleControlChange} required
                                          value={this.state.subExam.room}>
                                {this.props.rooms.map((room, index) => <option
                                    key={index}>{room.id} {room.number} {room.localization}</option>)}
                            </Form.Control>
                        </FormGroup>
                    </Form.Row>

                    <Form.Row className={"justify-content-center"}>
                        <Button variant={"secondary"} className={"custom-margins"} onClick={this.addSubexam}>
                            Dodaj subegzamin
                        </Button>
                        <Button variant={"success"} className={"custom-margins"}
                                onClick={this.handleSaveAndOpenConfirm}>
                            Wyślij wszystkie dodane subegzaminy
                        </Button>
                    </Form.Row>
                </Form>

                <Modal show={this.state.showConfirmationModal} onHide={this.handleCloseConfirmationModal}>
                    <Modal.Body>
                        <h4 className={"text-center"}>Egzamin i subegzaminy zostały dodane pomyślnie</h4>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant={"success"} onClick={this.handleCloseConfirmationModal}>OK</Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default NewSubExamInfo;
