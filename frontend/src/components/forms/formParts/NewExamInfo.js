import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";
import NewSubExamInfo from "./NewSubexamInfo";

class NewExamInfo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddNewSubexam: false,

        }
    }

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    setShowAddNew = (show) => {
        this.setState({
            showAddNewSubexam: show
        });
    }

    handleShowSubexam = () => this.setShowAddNew(true);
    handleHideSubexam = () => this.setShowAddNew(false);

    saveSubexam= (resolve, reject) =>{
        let form = this.formRef.current;
        if (!form.checkValidity()) {
            this.setValidated(true);
            reject();
        }
        else {
            this.props.handleHide();
            let userDataToSend = {
                Exam: this.state.userData.name,
                startDate: this.state.userData.startDate,
                endDate: this.state.userData.endDate,
                room: {
                    localization: this.state.userData.localization,
                    number: this.state.userData.number
                }
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
                    this.setState({
                        userData: this.getInitialState()
                    });
                    this.setValidated(false);
                    resolve();
                })
                .catch((error) => console.log(error));
        }
    }

    render() {
        let majorList = [];
        majorList = this.props.majors.map((major, index) => {
            return <option key={index} value={major}>{major}</option>
        });
        let buildingList = [];
        buildingList = this.props.buildings.map((building, index) => {
            return <option key={index} value={building}>{building}</option>
        });
        let roomList = [];
        roomList = this.props.rooms.map((rooms, index) => {
            return <option key={index} value={rooms}>{rooms}</option>
        });

        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o egzaminie</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"name"}>
                        <Form.Label>Egzamin</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"major"}>
                        <Form.Label>Kierunek</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {majorList}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"startDate"}>
                        <Form.Label>Data rozpoczęcia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"endDate"}>
                        <Form.Label>Data zakończenia</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                </Form.Row>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o subegzaminie</h5>
                    <Button variant={"secondary"} className={"custom-margins"} onClick={this.handleShowSubexam}>
                        Dodaj subegzamin
                    </Button>
                <NewSubExamInfo options={this.props.options} getFormData={this.getFormData}
                                buildings={this.state.buildings}
                                rooms={this.state.rooms}
                                handleShow={this.handleShowSubexam}
                                handleHide={this.handleHideSubexam}
                                show={this.state.showAddNewSubexam}/>
            </>
        );
    }
}

export default NewExamInfo;
