import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewFacultyInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o wydziale</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"name"}>
                        <Form.Label>Nazwa</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.name}/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"acronym"}>
                        <Form.Label>Skrót</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.acronym}/>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewFacultyInfo;