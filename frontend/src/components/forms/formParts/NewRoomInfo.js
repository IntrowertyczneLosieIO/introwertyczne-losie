import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewRoomInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o sali</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"localization"}>
                        <Form.Label>Budynek</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"number"}>
                        <Form.Label>Numer sali</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"recommendedCapacity"}>
                        <Form.Label>Rekomendowana pojemność sali</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"maximalCapacity"}>
                        <Form.Label>Maksymalna pojemność sali</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required/>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewRoomInfo;