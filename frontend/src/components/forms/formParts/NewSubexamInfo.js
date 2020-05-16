import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";
import Button from "react-bootstrap/Button";

class NewSubExamInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o subegzaminie</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"date"}>
                        <Form.Label>Dzień egzaminu</Form.Label>
                        <Form.Control type={"date"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"startTime"}>
                        <Form.Label>Godzina rozpoczęcia</Form.Label>
                        <Form.Control type={"time"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"endTime"}>
                        <Form.Label>Godzina zakończenia</Form.Label>
                        <Form.Control type={"time"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"building"}>
                        <Form.Label>Budynek</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {this.props.buildings}
                        </Form.Control>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"room"}>
                        <Form.Label>Sala</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required>
                            {/*<option hidden disabled value={"default"} />*/}
                            {this.props.rooms}
                        </Form.Control>
                    </FormGroup>
                </Form.Row>

                <Button variant={"secondary"} className={"custom-margins"} onClick={this.handleSaveAndOpenConfirm}>
                    Dodaj subegzamin
                </Button>
            </>
        );
    }
}

export default NewSubExamInfo;
