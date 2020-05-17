import React from "react";
import CentralHeader from "../dashboard/CentralHeader";
import Form from "react-bootstrap/Form";
import FormLabel from "react-bootstrap/FormLabel";
import FormControl from "react-bootstrap/FormControl";
import Col from "react-bootstrap/Col";
import FormGroup from "react-bootstrap/FormGroup";
import Row from "react-bootstrap/Row";
import Button from "react-bootstrap/Button";
import {Formik} from "formik";

const SignIn = () => (
    <Formik
        initialValues={{email: "", password: ""}}
        onSubmit={(values, {setSubmitting}) => {
            console.log(values);
            setSubmitting(false);
        }}
        validate={
            values => {
                let errors = {};
                // const emailRegex = /(^([a-zA-Z0-9_\-\.]+)@(agh)\.(edu)\.(pl))/;
                // const passwordRegex = /(?=.*[0-9])/;
                if (!values.email) {
                    errors.email = "Email jest wymagany"
                }
                // else if (!emailRegex.test(values.email)) {
                //     errors.email = "Nieprawidłowy email"
                // }
                if (!values.password) {
                    errors.password = "Hasło jest wymagane"
                }
                // else if (!passwordRegex.test(values.password)) {
                //     errors.password = "Nieprawidłowe hasło"
                // } else if (values.password.length < 8) {
                //     errors.password = "Hasło musi mieć co najmniej 8 znaków"
                // }
                return errors;
            }
        }>
        {
            props => {
                const {
                    values,
                    errors,
                    touched,
                    isSubmitting,
                    handleSubmit,
                    handleChange,
                    handleBlur
                } = props;

                return (
                    <>
                        <div className={"wrapper bg-light"}>
                            <Row className={"justify-content-center no-gutters"}>
                                <CentralHeader
                                    pageTitle={"System rezerwacji sal komputerowych"}
                                    subTitle={"na potrzeby przeprowadzania egzaminów na studia II stopnia"}/>
                            </Row>

                            <Row className={"justify-content-center mt-4 no-gutters"}>
                                <Form as={Col} xs={{span: 4}}
                                      className={"justify-content-center pt-4 pl-4 pr-4 form-bg rounded shadow"}
                                      onSubmit={handleSubmit}>
                                    <h1 className={"text-center text-uppercase font-weight-light mb-4 text-light"}>Logowanie</h1>
                                    <FormGroup as={Col} className={"text-center"} controlId={"email"}>
                                        <FormLabel
                                            className={"text-light font-weight-light text-uppercase"}>Email</FormLabel>
                                        <FormControl type={"email"} onChange={handleChange} value={values.email}
                                                     onBlur={handleBlur}
                                                     className={errors.email && touched.email && "error"}/>
                                        {errors.email && touched.email && (
                                            <div className={"input-info"}>{errors.email}</div>
                                        )}
                                    </FormGroup>
                                    <FormGroup as={Col} className={"text-center"} controlId={"password"}>
                                        <FormLabel
                                            className={"text-light font-weight-light text-uppercase"}>Hasło</FormLabel>
                                        <FormControl type={"password"} onChange={handleChange} value={values.password}
                                                     onBlur={handleBlur}
                                                     className={errors.password && touched.password && "error"}/>
                                        {errors.password && touched.password && (
                                            <div className={"input-info"}>{errors.password}</div>
                                        )}
                                    </FormGroup>
                                    <Col className={"pb-4 pt-4"}>
                                        <Button type={"submit"} onClick={handleSubmit} className={"mt-4 mb-4"}
                                                disabled={isSubmitting}
                                                variant={"outline-light"} block>Zaloguj się</Button>
                                    </Col>
                                </Form>
                            </Row>
                            <div className={"finf text-center pt-4 font-weight-normal"}>
                                <p>Aby się zarejestrować skontaktuj się z administratorem pod adresem
                                    mailowym <code>a[at]b</code></p>
                            </div>
                        </div>
                    </>
                );
            }}
    </Formik>
);

export default SignIn;