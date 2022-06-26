function generatePDF(){
    const element = document.getElementById("CV");
    const opt = {
        filename:     'SkadeRapport.pdf',
        image:        { type: 'jpeg', quality: 1.50},
        jsPDF: { format: 'a4' }
    };
    html2pdf()
        .set(opt)
        .from(element)
        .save();

}