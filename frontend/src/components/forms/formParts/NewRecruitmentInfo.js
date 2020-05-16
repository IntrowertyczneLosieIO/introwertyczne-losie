import React from "react";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import Col from "react-bootstrap/Col";

class NewRecruitmentInfo extends React.Component {

    handleControlChange = (event) => {
        this.props.getFormData(event.target.id, event.target.value);
    }

    render() {
        return (
            <>
                <h5 className={"mt-4 text-secondary mb-3"}>Informacje o rekrutacji</h5>
                <Form.Row>
                    <FormGroup as={Col} controlId={"acronym"}>
                        <Form.Label>Skrót</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.acronym}/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"recruitmentStatus"}>
                        <Form.Label>Status rekrutacji</Form.Label>
                        <Form.Control type={"text"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.recruitmentStatus}/>
                    </FormGroup>
                </Form.Row>
                <Form.Row>
                    <FormGroup as={Col} controlId={"year"}>
                        <Form.Label>Rok</Form.Label>
                        <Form.Control type={"text"} pattern="[1-9][0-9]*" onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.year}/>
                    </FormGroup>
                    <FormGroup as={Col} controlId={"semester"}>
                        <Form.Label>Semestr</Form.Label>
                        <Form.Control as={"select"} onChange={this.handleControlChange} required
                                      value={this.props.inputValuesFromState.semester}>
                            {/*<option hidden disabled value={"default"} />*/}
                            <option value={"letni"}>letni</option>
                            <option>zimowy</option>
                        </Form.Control>
                    </FormGroup>
                </Form.Row>
            </>
        );
    }
}

export default NewRecruitmentInfo;